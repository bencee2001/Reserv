package hu.bme.onlabor.dal.dao.reservation

import hu.bme.onlabor.annotation.annotations.GenMapper
import hu.bme.onlabor.dal.model.reservation.Reservation

@GenMapper
class ReservationDaoImpl: ReservationDao {

    companion object{
        const val NAME = "ReservationDaoImpl"
    }

    override suspend fun getAll(): List<Reservation> {
        TODO("Not yet implemented")
    }

    override suspend fun save(data: Reservation): Reservation? {
        TODO("Not yet implemented")
    }


}