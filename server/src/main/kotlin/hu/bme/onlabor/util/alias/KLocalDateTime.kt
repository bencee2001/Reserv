package hu.bme.onlabor.util.alias

import hu.bme.onlabor.util.serializer.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

typealias KLocalDateTime = @Serializable(with = LocalDateTimeSerializer::class) LocalDateTime