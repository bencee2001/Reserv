package hu.bme.onlabor.security

import hu.bme.onlabor.dal.dao.user.UserDao
import io.ktor.server.auth.UserPasswordCredential

class AuthService(
    private val userDao: UserDao
) {

    suspend fun validatePrincipal(credentials: UserPasswordCredential): AuthPrincipal? {
        val username = credentials.name
        val user = userDao.findByUsername(username) ?: return null
        val password = credentials.password
        return if (password == user.password) AuthPrincipal(user) else null
    }

}