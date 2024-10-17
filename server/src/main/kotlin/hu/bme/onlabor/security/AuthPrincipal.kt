package hu.bme.onlabor.security

import hu.bme.onlabor.dal.model.user.User
import io.ktor.server.auth.Principal

data class AuthPrincipal(
    val username: String,
    val password: String,
    val role: String
): Principal {
    constructor(user: User): this(user.username, user.password, user.role.name)
}