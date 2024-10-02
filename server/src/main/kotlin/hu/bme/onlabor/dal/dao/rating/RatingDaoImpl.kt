package hu.bme.onlabor.dal.dao.rating

import hu.bme.onlabor.annotation.annotations.GenMapper
import hu.bme.onlabor.dal.model.rating.Rating

@GenMapper
class RatingDaoImpl: RatingDao {

    companion object{
        const val NAME = "RatingDaoImpl"
    }

    override suspend fun getAll(): List<Rating> {
        TODO("Not yet implemented")
    }

    override suspend fun save(data: Rating): Rating? {
        TODO("Not yet implemented")
    }
}