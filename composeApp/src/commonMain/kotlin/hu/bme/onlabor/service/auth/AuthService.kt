package hu.bme.onlabor.service.auth

import hu.bme.onlabor.commondomain.model.Role
import hu.bme.onlabor.commondomain.network.response.AuthResponse


class AuthService{

    private var _authUser: AuthUser? = null
    private var _authToken: String? = null

    fun setAuth(auth: AuthResponse) {
        _authToken = auth.token
        _authUser = AuthUser(
            id = auth.userId,
            name = auth.name,
            username = auth.username,
            email = auth.email,
            role = auth.role,
            userLevel = auth.userLevel,
        )
    }

    fun getAuthToken(): String {
        return _authToken ?: throw IllegalStateException("Not authenticated yet.")
    }

    fun getAuthUser(): AuthUser {
        return _authUser ?: throw IllegalStateException("Not authenticated yet.")
    }

    fun clear(){
        _authUser = null
        _authToken = null
    }

    fun getRole(): Role {
        return _authUser?.role ?: throw IllegalStateException("Not authenticated yet.")
    }

}