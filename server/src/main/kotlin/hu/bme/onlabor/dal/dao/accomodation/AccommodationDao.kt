package hu.bme.onlabor.dal.dao.accomodation

import hu.bme.onlabor.annotation.interfaces.AbstractDao
import hu.bme.onlabor.dal.model.accommodation.Accommodation

interface AccommodationDao: AbstractDao<Accommodation>{
    suspend fun findByOwnerId(ownerId:Int):List<Accommodation>
}