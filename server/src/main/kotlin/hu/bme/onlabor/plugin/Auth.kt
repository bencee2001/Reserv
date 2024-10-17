package hu.bme.onlabor.plugin

import hu.bme.onlabor.security.AuthService
import hu.bme.onlabor.security.AuthType
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.form
import io.ktor.server.response.respond
import org.koin.ktor.ext.inject

fun Application.configureAuthenticate(){

    val authService by inject<AuthService>()

    install(Authentication){
        form(AuthType.AUTH_FORM._name) {
            userParamName = "username"
            passwordParamName = "password"
            validate { credentials ->
                authService.validatePrincipal(credentials)
            }
            challenge {
                call.respond(HttpStatusCode.Unauthorized, "Credentials are not valid")
            }
        }
    }
}