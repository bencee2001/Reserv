package hu.bme.onlabor.dal

import hu.bme.onlabor.dal.model.accommodation.Accommodations
import hu.bme.onlabor.dal.model.accommodation_picture.AccommodationPictures
import hu.bme.onlabor.dal.model.opentime.OpenTimes
import hu.bme.onlabor.dal.model.rating.Ratings
import hu.bme.onlabor.dal.model.reservation.Reservations
import hu.bme.onlabor.dal.model.user.Users
import org.jetbrains.exposed.sql.Table

val tables = arrayOf<Table>(
    Users,
    Accommodations,
    AccommodationPictures,
    OpenTimes,
    Reservations,
    Ratings,
)