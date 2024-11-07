package hu.bme.onlabor.plugin

import hu.bme.onlabor.routing.AuthRouting.authRouting
import hu.bme.onlabor.routing.TestRouting.testRouting
import io.ktor.server.application.Application
import io.ktor.server.routing.routing

fun Application.configureRouting() {

    routing {
        authRouting()
        testRouting()
    }

}
