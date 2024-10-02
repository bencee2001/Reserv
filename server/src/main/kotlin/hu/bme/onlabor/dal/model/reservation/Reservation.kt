package hu.bme.onlabor.dal.model.reservation

import hu.bme.onlabor.annotation.annotations.MapTo
import hu.bme.onlabor.annotation.annotations.MapperDataSide
import hu.bme.onlabor.annotation.annotations.MapperEntitySide
import hu.bme.onlabor.dal.dao.reservation.ReservationDaoImpl
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

@Serializable
@MapperDataSide(ReservationDaoImpl.NAME)
data class Reservation(
    @MapTo("id")
    val reservationId: Int? = null,
    val userId: Int,
    val accomId: Int,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime
)

@MapperEntitySide(ReservationDaoImpl.NAME)
object Reservations: Table(){
    val id = integer(Reservation::reservationId.name).autoIncrement()
    val userId = integer(Reservation::userId.name)
    val accomId = integer(Reservation::accomId.name)
    val startTime = datetime(Reservation::startTime.name)
    val endTime = datetime(Reservation::endTime.name)
}