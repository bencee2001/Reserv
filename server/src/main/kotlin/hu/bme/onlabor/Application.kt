package hu.bme.onlabor

import hu.bme.onlabor.dal.dbconnection.provideDataSource
import hu.bme.onlabor.dal.tables
import hu.bme.onlabor.dal.dao.user.UserDao
import hu.bme.onlabor.dal.model.user.User
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