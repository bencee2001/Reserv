package hu.bme.onlabor.service.auth

import hu.bme.onlabor.commondomain.network.response.AuthResponse


class AuthService{

    private var _authUser: AuthUser? = null
    private var _authToken: String? = null

    fun setAuth(auth: AuthResponse) {
        _authToken = auth.token
        _authUser = AuthUser(
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

}