package hu.bme.onlabor

import hu.bme.onlabor.plugin.configureDatabases
import hu.bme.onlabor.plugin.configureKoin
import hu.bme.onlabor.plugin.configureRouting
import hu.bme.onlabor.plugin.configureSerialization
import hu.bme.onlabor.plugin.configureSwagger
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

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
    configureSwagger()
}





