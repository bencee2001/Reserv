package hu.bme.onlabor.dal.dao.accomodation

import hu.bme.onlabor.annotation.interfaces.AbstractDao
import hu.bme.onlabor.dal.model.accommodation.Accommodation

interface AccommodationDao: AbstractDao {
    fun getAccommodations(): List<Accommodation>
    fun save(accommodation: Accommodation)
}