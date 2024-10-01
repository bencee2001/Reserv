package hu.bme.onlabor.dal.dao.accomodation

import hu.bme.onlabor.annotation.annotations.GenMapper
import hu.bme.onlabor.dal.model.accommodation.Accommodation

@GenMapper
class AccommodationDaoImpl: AccommodationDao {

    companion object{
        const val NAME = "AccommodationDaoImpl"
    }

    override fun getAccommodations(): List<Accommodation> {
        TODO("Not yet implemented")
    }

    override fun save(accommodation: Accommodation) {
        TODO("Not yet implemented")
    }

}