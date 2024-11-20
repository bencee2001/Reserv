package hu.bme.onlabor.security

import hu.bme.onlabor.commondomain.model.Role
import hu.bme.onlabor.dal.dao.user.UserDao
import io.ktor.server.auth.UserPasswordCredential
import io.ktor.server.auth.jwt.JWTCredential
import io.ktor.server.auth.jwt.JWTPrincipal

class AuthService(
    private val userDao: UserDao
) {

    suspend fun validatePrincipal(credentials: UserPasswordCredential): AuthPrincipal? {
        val username = credentials.name
        val user = userDao.findByUsername(username) ?: return null
        val password = credentials.password
        return if (password == user.password) AuthPrincipal(user) else null
    }

    suspend fun validateJwtCredential(jwtCredential: JWTCredential, authRole: Role? = null): JWTPrincipal? {
        val username = jwtCredential.payload.getClaim(JWTNames.USERNAME_PARAM).asString()
        val user = userDao.findByUsername(username) ?: return null
        val password = jwtCredential.payload.getClaim(JWTNames.PASSWORD_PARAM).asString()
        return if (password == user.password && checkRole(authRole, user.role))
            JWTPrincipal(jwtCredential.payload)
        else
            null
    }

    private fun checkRole(
        authRole: Role?,
        userRole: Role
    ): Boolean {
        return if (authRole != null)
            authRole == userRole
        else
            true
    }

}