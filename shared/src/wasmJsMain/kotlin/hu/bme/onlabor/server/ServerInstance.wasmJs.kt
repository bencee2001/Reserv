package hu.bme.onlabor.server

import hu.bme.onlabor.commondomain.TEST_WASM_SERVER_URL
import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

actual fun getServerClient(): ServerClient {
    return ServerClient(
        HttpClient(Js.create()){
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                    explicitNulls = false
                })
            }
        },
        TEST_WASM_SERVER_URL
    )
}