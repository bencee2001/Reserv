package hu.bme.onlabor.dal.dao.accommodation_picture

import hu.bme.onlabor.annotation.annotations.GenMapper
import hu.bme.onlabor.dal.model.accommodation_picture.AccommodationPicture

@GenMapper
class AccommodationPictureDaoImpl: AccommodationPictureDao {

    companion object{
        const val NAME = "AccommodationPictureDaoImpl"
    }

    override suspend fun getAll(): List<AccommodationPicture> {
        TODO("Not yet implemented")
    }

    override suspend fun save(data: AccommodationPicture): AccommodationPicture? {
        TODO("Not yet implemented")
    }
}