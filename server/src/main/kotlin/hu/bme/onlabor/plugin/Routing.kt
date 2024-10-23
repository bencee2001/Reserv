package hu.bme.onlabor.plugin

import hu.bme.onlabor.dal.dao.user.UserDao
import hu.bme.onlabor.dal.model.user.User
import hu.bme.onlabor.security.AuthPrincipal
import hu.bme.onlabor.security.AuthType
import hu.bme.onlabor.security.JWTManager
import io.github.smiley4.ktorswaggerui.dsl.post
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.principal
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import kotlinx.serialization.Serializable
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    routing {
        login()
        register()
        getHello()
        testAuthenticated()
    }
}

fun Route.getHello() {
    get("/Hello") {
        call.respondText("Ktor: Hello")
    }
}

fun Route.testAuthenticated() {
    authenticate(AuthType.AUTH_JWT_ALL._name) {
        get("/authenticated") {
            call.respondText("Ktor: Hello authenticated.")
        }
    }

}

fun Route.login() {
    val jwtManager by inject<JWTManager>()
    authenticate(AuthType.AUTH_FORM._name) {
        post("/login") {
            val principal = call.principal<AuthPrincipal>()
                ?: throw IllegalStateException("No user for same reason.")
            val token = jwtManager.generateJWT(principal)
            call.respondText(token)
        }
    }
}

fun Route.register() {

    val userDao by inject<UserDao>()

    post("/register",{
        request {
            body<RegisterRequest>{
                description = "User with username and password"
                required = true

                example("First", RegisterRequest(name = "Test Elek", username = "Arjun", email = "asd@gmail.com", password = "password")) {
                    description = "A longer description of the example"
                    summary = "Default login credential to get token"
                }
            }
        }

    }) {
        val request = call.receive<RegisterRequest>()
        if (request.email.isBlank() || request.username.isBlank() || request.password.isBlank()) {
            call.respond(HttpStatusCode.BadRequest, "Username or Password can not be empty.")
            return@post
        }
        val savedUser = userDao.save(
            User(
                name = request.name,
                email = request.email,
                username = request.username,
                password = request.password
            )
        )
        if (savedUser != null) {
            call.respond(HttpStatusCode.Created, "User registered successfully")
        } else {
            call.respond(HttpStatusCode.Conflict, "User already exists")
        }
    }
}

@Serializable
data class RegisterRequest(
    val name: String,
    val email: String,
    val username: String,
    val password: String
)