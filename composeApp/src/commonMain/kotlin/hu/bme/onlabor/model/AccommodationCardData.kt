package hu.bme.onlabor.model

import hu.bme.onlabor.commondomain.countries.CountriesProvider
import hu.bme.onlabor.commondomain.countries.Country
import hu.bme.onlabor.commondomain.network.response.AccommodationResponse

data class AccommodationCardData(
    val name: String = "test",
    val ownerId: Int = 1,
    val country: Country = CountriesProvider.getCountries().first(),
    val mainImageUrl: String = "https://cdn.pixabay.com/photo/2023/12/17/09/47/door-8453898_1280.jpg",
    val city: String = "test",
    val address: String = "test",
    val ratingAvg: Double = 0.0,
    val ratingCount: Int = 0
) {
    constructor(accommodationResponse: AccommodationResponse): this(
        name = accommodationResponse.name,
        ownerId = accommodationResponse.ownerId,
        country = accommodationResponse.country,
        mainImageUrl = accommodationResponse.mainImageUrl,
        city = accommodationResponse.city,
        address = accommodationResponse.address,
        ratingAvg = accommodationResponse.ratingAvg,
        ratingCount = accommodationResponse.ratingCount
    )
}