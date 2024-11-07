package hu.bme.onlabor.server

import hu.bme.onlabor.commondomain.network.request.RegisterRequest
import hu.bme.onlabor.commondomain.network.response.AuthResponse
import hu.bme.onlabor.commondomain.network.response.BasicResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.*
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.Parameters
import io.ktor.http.appendPathSegments
import io.ktor.http.contentType

class ServerClient (
    private val client: HttpClient,
    private val serverUrl: String
): AuthClient {

    suspend fun getHello(): String {
        val response = client.get(serverUrl) {
            url {
                appendPathSegments("/Hello")
            }
        }
        return response.body<String>()
    }

    override suspend fun login(username: String, password: String): HttpResponse {
        val response = client.post(serverUrl) {
            url {
                appendPathSegments("/auth/login")
            }
            contentType(ContentType.Application.FormUrlEncoded)
            setBody(FormDataContent(Parameters.build {
                append("username", username)
                append("password", password)
            }))
        }
        return response
    }

    override suspend fun register(
        name: String,
        username: String,
        password: String,
        email: String
    ): BasicResponse<String> {
        val request = RegisterRequest(
            name, email, username, password
        )
        val response = client.post(serverUrl) {
            url {
                appendPathSegments("/auth/register")
            }
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        val status = response.status.value
        val bodyMessage = response.body<String>()
        return BasicResponse(status, bodyMessage)
    }

    suspend fun testAuth(token: String): String {
        val response = client.get(serverUrl) {
            url {
                appendPathSegments("/authenticated")
            }
            headers {
                append(HttpHeaders.Authorization, "Bearer $token")
            }
        }
        return response.body<String>()
    }
}