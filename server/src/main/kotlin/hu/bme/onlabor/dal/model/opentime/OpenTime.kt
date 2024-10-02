package hu.bme.onlabor.dal.model.opentime

import hu.bme.onlabor.annotation.annotations.MapTo
import hu.bme.onlabor.annotation.annotations.MapperDataSide
import hu.bme.onlabor.annotation.annotations.MapperEntitySide
import hu.bme.onlabor.dal.dao.opentime.OpenTimeDaoImpl
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

@Serializable
@MapperDataSide(OpenTimeDaoImpl.NAME)
class OpenTime (
    @MapTo("id")
    val openTimeId: Int?,
    val accomId: Int,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime
)

@MapperEntitySide(OpenTimeDaoImpl.NAME)
object OpenTimes: Table(){
    val id = integer(OpenTime::openTimeId.name).autoIncrement()
    val accomId = integer(OpenTime::accomId.name)
    val startTime = datetime(OpenTime::startTime.name)
    val endTime = datetime(OpenTime::endTime.name)
}