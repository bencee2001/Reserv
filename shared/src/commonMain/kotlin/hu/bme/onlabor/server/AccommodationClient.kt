package hu.bme.onlabor.server

interface AccommodationClient {
    suspend fun getAccommodations()
}