package hu.bme.onlabor.plugin

import hu.bme.onlabor.commondomain.model.Role
import hu.bme.onlabor.security.AuthService
import hu.bme.onlabor.security.AuthType
import hu.bme.onlabor.security.JWTManager
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.form
import io.ktor.server.auth.jwt.jwt
import io.ktor.server.response.respond
import org.koin.ktor.ext.inject

fun Application.configureAuthenticate(){

    val authService by inject<AuthService>()
    val jwtManager by inject<JWTManager>()

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
        jwt(AuthType.AUTH_JWT_ALL._name) {
            realm = "all_user"
            verifier(jwtManager.verifier)
            validate { jwtCredential ->
                authService.validateJwtCredential(jwtCredential)
            }
            challenge { defaultScheme, realm ->
                call.respond(HttpStatusCode.Unauthorized, "Token is not valid or has expired.")
            }
        }
        jwt(AuthType.AUTH_JWT_OWNER._name) {
            realm = "owner_user"
            verifier(jwtManager.verifier)
            validate { jwtCredential ->
                authService.validateJwtCredential(jwtCredential, Role.OWNER)
            }
            challenge { defaultScheme, realm ->
                call.respond(HttpStatusCode.Unauthorized, "Token is not valid or has expired or do not have Owner status.")
            }
        }
    }
}