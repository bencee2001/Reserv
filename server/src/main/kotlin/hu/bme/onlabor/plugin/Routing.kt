package hu.bme.onlabor.plugin

import hu.bme.onlabor.security.AuthPrincipal
import hu.bme.onlabor.security.AuthType
import hu.bme.onlabor.security.JWTManager
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.principal
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    val jwtManager by inject<JWTManager>()

    routing {
        authenticate(AuthType.AUTH_FORM._name) {
            post("/login") {
                val principal = call.principal<AuthPrincipal>() ?: throw IllegalStateException("No user for same reason.")
                val token = jwtManager.generateJWT(principal)
                call.respondText(token)
            }
        }
        getHello()
    }
}

fun Route.getHello() {
    get("/Hello") {
        call.respondText("Ktor: Hello")
    }
}