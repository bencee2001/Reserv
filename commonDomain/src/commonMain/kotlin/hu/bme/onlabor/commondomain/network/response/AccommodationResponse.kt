package hu.bme.onlabor.commondomain.network.response

import hu.bme.onlabor.commondomain.countries.CountriesProvider
import hu.bme.onlabor.commondomain.countries.Country

data class AccommodationResponse(
    val name: String,
    val ownerId: Int,
    val country: Country,
    val mainImageUrl: String,
    val city: String,
    val address: String,
    val ratingAvg: Double,
    val ratingCount: Int
)