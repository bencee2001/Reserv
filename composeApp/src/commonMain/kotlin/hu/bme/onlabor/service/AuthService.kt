package hu.bme.onlabor.service

import hu.bme.onlabor.server.ServerClient

class AuthService{

    private var _authToken: String? = null

    fun setAuthToken(token: String) {
        _authToken = token
    }

    fun getAuthToken(): String {
        return _authToken ?: throw IllegalStateException("Not authenticated yet.")
    }

}