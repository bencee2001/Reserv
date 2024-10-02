package hu.bme.onlabor

import hu.bme.onlabor.dal.dao.user.UserDao
import hu.bme.onlabor.dal.dao.user.UserDaoImpl
import hu.bme.onlabor.enviroment.ApplicationEnv
import hu.bme.onlabor.enviroment.DevEnv
import org.koin.dsl.module

val koinAppModule = module {

    single<UserDao> {
        UserDaoImpl()
    }

    single<ApplicationEnv>{ //TODO gradle env param
        DevEnv()
    }
}