package hu.bme.onlabor

import hu.bme.onlabor.dal.dao.user.UserDao
import hu.bme.onlabor.dal.dao.user.UserDaoImpl
import org.koin.dsl.module

val koinAppModule = module {

    single<UserDao> {
        UserDaoImpl()
    }
}