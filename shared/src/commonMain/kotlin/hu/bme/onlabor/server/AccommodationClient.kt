package hu.bme.onlabor.server

import io.ktor.client.statement.*

interface AccommodationClient {
    suspend fun getAccommodations(token: String): HttpResponse
}