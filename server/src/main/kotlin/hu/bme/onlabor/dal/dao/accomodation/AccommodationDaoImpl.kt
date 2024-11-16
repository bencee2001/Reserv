package hu.bme.onlabor.dal.dao.accomodation

import hu.bme.onlabor.annotation.annotations.GenMapper
import hu.bme.onlabor.dal.dbQuery
import hu.bme.onlabor.dal.model.accommodation.Accommodation
import hu.bme.onlabor.dal.model.accommodation.Accommodations
import hu.bme.onlabor.gen.resultRowToAccommodation
import org.jetbrains.exposed.sql.selectAll

@GenMapper
class AccommodationDaoImpl: AccommodationDao {

    companion object{
        const val NAME = "AccommodationDaoImpl"
    }

    override suspend fun getAll(): List<Accommodation> = dbQuery {
        Accommodations.selectAll().map { resultRowToAccommodation(it) }
    }

    override suspend fun findByOwnerId(ownerId: Int): List<Accommodation> = dbQuery {
        Accommodations
            .selectAll()
            .where { Accommodations.ownerId eq ownerId}
            .map { resultRowToAccommodation(it) }
    }

    override suspend fun save(data: Accommodation): Accommodation? {
        TODO("Not yet implemented")
    }


}