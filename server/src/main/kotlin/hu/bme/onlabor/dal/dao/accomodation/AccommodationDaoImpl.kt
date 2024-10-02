package hu.bme.onlabor.dal.dao.accomodation

import hu.bme.onlabor.annotation.annotations.GenMapper
import hu.bme.onlabor.dal.model.accommodation.Accommodation

@GenMapper
class AccommodationDaoImpl: AccommodationDao {

    companion object{
        const val NAME = "AccommodationDaoImpl"
    }

    override suspend fun getAll(): List<Accommodation> {
        TODO("Not yet implemented")
    }

    override suspend fun save(data: Accommodation): Accommodation? {
        TODO("Not yet implemented")
    }


}