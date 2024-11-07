package hu.bme.onlabor.dal.model.accommodation

import hu.bme.onlabor.annotation.annotations.MapTo
import hu.bme.onlabor.annotation.annotations.MapWith
import hu.bme.onlabor.annotation.annotations.MapperDataSide
import hu.bme.onlabor.annotation.annotations.MapperEntitySide
import hu.bme.onlabor.commondomain.countries.Country
import hu.bme.onlabor.dal.dao.accomodation.AccommodationDaoImpl
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
@MapperDataSide(AccommodationDaoImpl.NAME)
data class Accommodation(
    @MapTo("id")
    var accomId: Int? = null,
    var name: String = "test",
    var ownerId: Int,
    @MapWith(CountryAttributeMapper.PATH)
    var country: Country,
    var city: String,
    var address: String,
    var mainPictureUrl: String,
    var latitude: Double,
    var longitude: Double,
    var ratingAvg: Double,
    var ratingCount: Int
)

@MapperEntitySide(AccommodationDaoImpl.NAME)
object Accommodations: Table() {
    val id = integer(Accommodation::accomId.name).autoIncrement()
    val name = varchar(Accommodation::name.name, 100)
    val ownerId = integer(Accommodation::ownerId.name)
    val country = varchar(Accommodation::country.name, 3)
    val city = varchar(Accommodation::city.name, 100)
    val address = varchar(Accommodation::address.name, 255)
    val mainPictureUrl = varchar(Accommodation::mainPictureUrl.name, 255)
    val latitude = double(Accommodation::latitude.name)
    val longitude = double(Accommodation::longitude.name)
    val ratingAvg = double(Accommodation::ratingAvg.name)
    val ratingCount = integer(Accommodation::ratingCount.name)

    override val primaryKey: PrimaryKey
        get() = PrimaryKey(Accommodations.id)
}