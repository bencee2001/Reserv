package hu.bme.onlabor

import hu.bme.onlabor.dal.model.user.UserDao
import hu.bme.onlabor.dal.model.user.UserDaoImpl
import org.koin.dsl.module

val koinAppModule = module {

    single<UserDao> {
        UserDaoImpl()
    }
}