package hu.bme.onlabor

import hu.bme.onlabor.dal.provideDataSource
import hu.bme.onlabor.dal.tables
import hu.bme.onlabor.dal.dao.user.UserDao
import hu.bme.onlabor.enviroment.ApplicationEnv
import hu.bme.onlabor.plugin.configureDatabases
import hu.bme.onlabor.plugin.configureKoin
import hu.bme.onlabor.plugin.configureRouting
import hu.bme.onlabor.plugin.configureSerialization
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
    configureRouting()
}





