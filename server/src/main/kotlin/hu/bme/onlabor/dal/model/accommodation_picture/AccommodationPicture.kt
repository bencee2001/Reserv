package hu.bme.onlabor.dal.model.accommodation_picture

import hu.bme.onlabor.annotation.annotations.MapTo
import hu.bme.onlabor.annotation.annotations.MapperDataSide
import hu.bme.onlabor.annotation.annotations.MapperEntitySide
import hu.bme.onlabor.dal.dao.accommodation_picture.AccommodationPictureDaoImpl
import hu.bme.onlabor.dal.dao.accomodation.AccommodationDaoImpl
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
@MapperDataSide(AccommodationPictureDaoImpl.NAME)
data class AccommodationPicture(
    @MapTo("id")
    val accomPicId: Int? = null,
    val accomId: Int,
    val pictureUrl: String
)

@MapperEntitySide(AccommodationPictureDaoImpl.NAME)
object AccommodationPictures: Table() {
    val id = integer(AccommodationPicture::accomPicId.name).autoIncrement()
    val accomId = integer(AccommodationPicture::accomId.name)
    val pictureUrl = varchar(AccommodationPicture::pictureUrl.name, 255)
}