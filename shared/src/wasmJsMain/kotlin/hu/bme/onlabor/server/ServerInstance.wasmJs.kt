package hu.bme.onlabor.server

import hu.bme.onlabor.commondomain.TEST_WASM_SERVER_URL
import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js

actual fun getServerClient(): ServerClient {
    return ServerClient(HttpClient(Js), TEST_WASM_SERVER_URL)
}