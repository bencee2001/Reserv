package hu.bme.onlabor.security

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import hu.bme.onlabor.security.JWTNames.PASSWORD_PARAM
import hu.bme.onlabor.security.JWTNames.ROLE_PARAM
import hu.bme.onlabor.security.JWTNames.USERNAME_PARAM
import java.util.Date

class JWTManager(private val authKey: String) {

    val verifier: JWTVerifier = JWT
        .require(Algorithm.HMAC256(authKey))
        .build()

    fun generateJWT(principal: AuthPrincipal): String{
        return JWT.create()
            .withClaim(USERNAME_PARAM, principal.username)
            .withClaim(PASSWORD_PARAM, principal.password)
            .withClaim(ROLE_PARAM, principal.role)
            .withExpiresAt(Date(System.currentTimeMillis() + 60_000))
            .sign(Algorithm.HMAC256(authKey))
    }

    fun decodeJWT(token: String): DecodedJWT {
        val verifier = JWT.require(Algorithm.HMAC256(authKey))
            .build()
        return verifier.verify(token)
    }

}

