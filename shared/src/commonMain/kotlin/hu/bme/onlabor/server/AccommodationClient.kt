package hu.bme.onlabor.server

import io.ktor.client.statement.*

interface AccommodationClient {
    suspend fun getAccommodations(token: String): HttpResponse

    suspend fun getOwnAccommodations(token: String, userId: Int): HttpResponse
}