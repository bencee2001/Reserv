package hu.bme.onlabor.routing

import hu.bme.onlabor.commondomain.network.request.RegisterRequest
import hu.bme.onlabor.commondomain.network.response.AuthResponse
import hu.bme.onlabor.dal.dao.user.UserDao
import hu.bme.onlabor.dal.model.user.User
import hu.bme.onlabor.security.AuthPrincipal
import hu.bme.onlabor.security.AuthType
import hu.bme.onlabor.security.JWTManager
import io.github.smiley4.ktorswaggerui.dsl.route
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

object AuthRouting {
    fun Route.authRouting() {
        route("/auth", {
            tags = listOf("Auth")
        }) {
            loginRouting()
            registerRouting()
        }
    }

    private fun Route.loginRouting() {
        val jwtManager by inject<JWTManager>()
        val userDao by inject<UserDao>()
        authenticate(AuthType.AUTH_FORM._name) {
            post("/login") {
                val principal = call.principal<AuthPrincipal>()
                    ?: throw IllegalStateException("No user for same reason.")
                val token = jwtManager.generateJWT(principal)
                val user = userDao.findByUsername(principal.username)!!
                val authResponse = AuthResponse(
                    name = user.name,
                    username = user.username,
                    email = user.email,
                    role = user.role,
                    userLevel = user.userLevel,
                    token = token
                )
                call.respond(authResponse)
            }
        }
    }

    private fun Route.registerRouting() {
        val userDao by inject<UserDao>()

        post("/register") {
            val request = call.receive<RegisterRequest>()
            if (request.name.isBlank() || request.email.isBlank() || request.username.isBlank() || request.password.isBlank()) {
                call.respond(HttpStatusCode.BadRequest, "Username or Password can not be empty.")
                return@post
            }
            if(userDao.findByUsername(request.username) != null) {
                call.respond(HttpStatusCode.Conflict, "Username already exists.")
            } else if (userDao.findByEmail(request.email) != null) {
                call.respond(HttpStatusCode.Conflict, "User with this Email already exists.")
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
}