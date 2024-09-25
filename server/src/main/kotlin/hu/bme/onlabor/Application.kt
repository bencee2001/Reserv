package hu.bme.onlabor

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeSpec
import hu.bme.onlabor.dal.dbconnection.provideDataSource
import hu.bme.onlabor.dal.dbconnection.tables
import hu.bme.onlabor.dal.model.user.User
import hu.bme.onlabor.dal.model.user.UserDao
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import java.io.File

fun main() {

    embeddedServer(
        Netty,
        port = SERVER_PORT,
        host = "0.0.0.0",
        module = Application::module
    ).start(wait = true)
}

fun Application.module() {
    configureKoin()
    configureSerialization()
    configureDatabases()

    val userDao by inject<UserDao>()

    routing {
        get("/") {
            call.respondText("Ktor: ${Greeting().greet()}")
            val t = userDao.save(User())
            println(t)
            val tt = userDao.getUsers()
            println(tt)
        }
    }
}

fun Application.configureKoin() {
    install(Koin){
        slf4jLogger()
        modules(koinAppModule)
    }
}

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
}

fun Application.configureDatabases() {
    val driverClass="org.postgresql.Driver"
    val jdbcUrl="jdbc:postgresql://localhost:5432/reserv?user=admin&password=1234"
    val db= Database.connect(provideDataSource(jdbcUrl,driverClass))
    transaction(db){
        SchemaUtils.create(*tables)
    }
}