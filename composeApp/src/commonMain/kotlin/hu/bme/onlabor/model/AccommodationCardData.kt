package hu.bme.onlabor.model

import hu.bme.onlabor.commondomain.countries.Country

data class AccommodationCardData(
    val name: String,
    val country: Country,
    val city: String,
    val ratingAvg: Double,
    val ratingCount: Int
)