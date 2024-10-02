package hu.bme.onlabor.dal.model.rating

import hu.bme.onlabor.annotation.annotations.MapTo
import hu.bme.onlabor.annotation.annotations.MapperDataSide
import hu.bme.onlabor.annotation.annotations.MapperEntitySide
import hu.bme.onlabor.dal.dao.rating.RatingDaoImpl
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
@MapperDataSide(RatingDaoImpl.NAME)
data class Rating (
    @MapTo("id")
    val ratingId: Int? = null,
    val accomId: Int,
    val rating: StarRating,
    val comment: String
)

@MapperEntitySide(RatingDaoImpl.NAME)
object Ratings: Table() {
    val id = integer(Rating::ratingId.name).autoIncrement()
    val accomId = integer(Rating::accomId.name)
    val rating = enumerationByName<StarRating>(Rating::rating.name, 10)
    val comment = varchar(Rating::comment.name, 255)


}