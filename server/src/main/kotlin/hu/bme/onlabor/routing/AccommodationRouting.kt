package hu.bme.onlabor.routing

import hu.bme.onlabor.commondomain.network.response.AccommodationsResponse
import hu.bme.onlabor.dal.dao.accomodation.AccommodationDao
import hu.bme.onlabor.dal.model.accommodation.toResponse
import hu.bme.onlabor.security.AuthType
import io.github.smiley4.ktorswaggerui.dsl.route
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

object AccommodationRouting {
    fun Route.accommodationRouting() {
        route("/accommodation", {
            tags = listOf("Accommodation")
        }) {
            getAllAccommodations()
        }
    }

    private fun Route.getAllAccommodations() {
        val accommodationDao by inject<AccommodationDao>()
        authenticate(AuthType.AUTH_JWT_ALL._name){
            get("/all") {
                val accommodationResponses = accommodationDao.getAll().map { it.toResponse() }
                call.respond(AccommodationsResponse(accommodationResponses))
            }
        }
    }
}