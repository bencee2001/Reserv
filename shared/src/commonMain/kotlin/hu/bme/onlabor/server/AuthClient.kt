package hu.bme.onlabor.server

import hu.bme.onlabor.response.BasicResponse

interface AuthClient {
    suspend fun login(username: String, password: String): BasicResponse<String>
}