package hu.bme.onlabor.plugin

import hu.bme.onlabor.dal.provideDataSource
import hu.bme.onlabor.dal.tables
import hu.bme.onlabor.enviroment.ApplicationEnv
import io.ktor.server.application.Application
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.ktor.ext.inject

fun Application.configureDatabases() {

    val applicationEnv by inject<ApplicationEnv>()

    val driverClass=applicationEnv.dbDriverClass
    val jdbcUrl=applicationEnv.dbUrl
    val db= Database.connect(provideDataSource(jdbcUrl,driverClass))
    transaction(db){
        SchemaUtils.create(*tables)
    }
}