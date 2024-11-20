package hu.bme.onlabor.commondomain.network.response

import hu.bme.onlabor.commondomain.model.Role
import hu.bme.onlabor.commondomain.model.UserLevel
import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse (
    val userId: Int,
    val name: String,
    val username: String,
    val role: Role,
    val email: String,
    val userLevel: UserLevel,
    val token: String
)