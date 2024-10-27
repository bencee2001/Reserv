package hu.bme.onlabor.server

import hu.bme.onlabor.commondomain.TEST_ANDROID_SERVER_URL
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp

actual fun getServerClient(): ServerClient {
    return ServerClient(HttpClient(OkHttp), TEST_ANDROID_SERVER_URL)
}