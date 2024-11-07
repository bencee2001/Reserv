package hu.bme.onlabor.server

import hu.bme.onlabor.commondomain.network.response.AuthResponse
import hu.bme.onlabor.commondomain.network.response.BasicResponse
import io.ktor.client.statement.*

interface AuthClient {
    suspend fun login(username: String, password: String): HttpResponse
    suspend fun register(name: String, username: String, password: String, email: String): BasicResponse<String>
}