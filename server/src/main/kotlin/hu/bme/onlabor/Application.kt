package hu.bme.onlabor

import hu.bme.onlabor.common.SERVER_PORT
import hu.bme.onlabor.plugin.configureAuthenticate
import hu.bme.onlabor.plugin.configureCors
import hu.bme.onlabor.plugin.configureDatabases
import hu.bme.onlabor.plugin.configureKoin
import hu.bme.onlabor.plugin.configureRouting
import hu.bme.onlabor.plugin.configureSerialization
import hu.bme.onlabor.plugin.configureSwagger
import io.ktor.server.application.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

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
    configureAuthenticate()
    configureRouting()
    configureSwagger()
    configureCors()
}





