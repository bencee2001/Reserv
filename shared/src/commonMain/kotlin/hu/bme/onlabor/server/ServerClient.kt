package hu.bme.onlabor.server

import hu.bme.onlabor.response.BasicResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.Parameters
import io.ktor.http.appendPathSegments
import io.ktor.http.contentType

class ServerClient (
    private val client: HttpClient,
    private val serverUrl: String
) {

    suspend fun getHello(): String {
        val response = client.get(serverUrl) {
            url {
                appendPathSegments("/Hello")
            }
        }
        return response.body<String>()
    }

    suspend fun login(username: String, password: String): BasicResponse<String> {
        val response = client.post(serverUrl) {
            url {
                appendPathSegments("/login")
            }
            contentType(ContentType.Application.FormUrlEncoded)
            setBody(FormDataContent(Parameters.build {
                append("username", username)
                append("password", password)
            }))
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