package hu.bme.onlabor.dal

import hu.bme.onlabor.dal.model.accommodation.Accommodations
import hu.bme.onlabor.dal.model.user.Users
import org.jetbrains.exposed.sql.Table

val tables = arrayOf<Table>(
    Users,
    Accommodations
)