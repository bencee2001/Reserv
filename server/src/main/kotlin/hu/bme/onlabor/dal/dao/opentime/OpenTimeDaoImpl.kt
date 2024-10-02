package hu.bme.onlabor.dal.dao.opentime

import hu.bme.onlabor.annotation.annotations.GenMapper
import hu.bme.onlabor.dal.model.opentime.OpenTime

@GenMapper
class OpenTimeDaoImpl: OpenTimeDao {

    companion object{
        const val NAME = "OpenTimeDaoImpl"
    }

    override suspend fun getAll(): List<OpenTime> {
        TODO("Not yet implemented")
    }

    override suspend fun save(data: OpenTime): OpenTime? {
        TODO("Not yet implemented")
    }
}