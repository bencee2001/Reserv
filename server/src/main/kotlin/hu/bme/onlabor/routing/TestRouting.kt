package hu.bme.onlabor.routing

import hu.bme.onlabor.security.AuthType
import io.github.smiley4.ktorswaggerui.dsl.route
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

object TestRouting {
    fun Route.testRouting() {
        route("/test", {
            tags = listOf("Test")
        }) {
            helloRouting()
            authenticatedRouting()
        }
    }

    private fun Route.helloRouting() {
        get("/Hello") {
            call.respondText("Ktor: Hello")
        }
    }

    private fun Route.authenticatedRouting() {
        authenticate(AuthType.AUTH_JWT_ALL._name) {
            get("/authenticated") {
                call.respondText("Ktor: Hello authenticated.")
            }
        }
    }
}