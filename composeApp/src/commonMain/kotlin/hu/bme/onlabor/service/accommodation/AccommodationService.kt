package hu.bme.onlabor.service.accommodation

import hu.bme.onlabor.commondomain.network.response.AccommodationsResponse
import hu.bme.onlabor.model.AccommodationCardData
import hu.bme.onlabor.server.AccommodationClient
import hu.bme.onlabor.service.auth.AuthService
import io.ktor.client.call.*

class AccommodationService(
    private val accommodationClient: AccommodationClient,
    private val authService: AuthService
) {
    private var accommodations: List<AccommodationCardData>? = null
    private var ownAccommodations: List<AccommodationCardData>? = null

    suspend fun loadAccommodation(): List<AccommodationCardData> {
        if (accommodations == null) {
            val response = accommodationClient.getAccommodations(authService.getAuthToken())
            val accommodationResponses = response.body<AccommodationsResponse>()
            val accommodationDataList = accommodationResponses.accommodationResponses.map { AccommodationCardData(it) }
            accommodations = accommodationDataList
        }
        return accommodations!!
    }

    suspend fun loadOwnAccommodation(): List<AccommodationCardData> {
        if (ownAccommodations == null) {
            val token = authService.getAuthToken()
            val userId = authService.getAuthUser().id
            val response = accommodationClient.getOwnAccommodations(token, userId)
            val accommodationResponses = response.body<AccommodationsResponse>()
            val accommodationDataList = accommodationResponses.accommodationResponses.map { AccommodationCardData(it) }
            ownAccommodations = accommodationDataList
        }
        return ownAccommodations!!
    }
}