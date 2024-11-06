package hu.bme.onlabor.commondomain.network.request

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val name: String,
    val email: String,
    val username: String,
    val password: String
)