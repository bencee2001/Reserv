package hu.bme.onlabor.commondomain.network.response

import kotlinx.serialization.Serializable

@Serializable
data class AccommodationsResponse(
    val accommodationResponses: List<AccommodationResponse>
)