package hu.bme.onlabor.routing

import hu.bme.onlabor.commondomain.network.response.AccommodationsResponse
import hu.bme.onlabor.dal.dao.accomodation.AccommodationDao
import hu.bme.onlabor.dal.model.accommodation.toResponse
import hu.bme.onlabor.security.AuthType
import io.github.smiley4.ktorswaggerui.dsl.route
import io.ktor.http.*
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
            getOwnAccommodation()
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

    private fun Route.getOwnAccommodation() {
        val accommodationDao by inject<AccommodationDao>()
        authenticate(AuthType.AUTH_JWT_OWNER._name){
            get("/own/{userId}") {
                val userId = call.parameters["userId"]?.toInt() ?: return@get call.respond(HttpStatusCode.BadRequest)
                val ownAccommodationResponses = accommodationDao.findByOwnerId(userId).map { it.toResponse() }
                call.respond(AccommodationsResponse(ownAccommodationResponses))
            }
        }
    }
}