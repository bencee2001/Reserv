package hu.bme.onlabor.dal.dbconnection

import hu.bme.onlabor.dal.model.user.Users
import org.jetbrains.exposed.sql.Table

val tables = arrayOf<Table>(
    Users
)