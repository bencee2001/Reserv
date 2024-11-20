package hu.bme.onlabor.service.auth

import hu.bme.onlabor.commondomain.model.Role
import hu.bme.onlabor.commondomain.model.UserLevel

data class AuthUser(
    val id: Int,
    val name : String,
    val username: String,
    val email : String,
    val role : Role,
    val userLevel: UserLevel
)