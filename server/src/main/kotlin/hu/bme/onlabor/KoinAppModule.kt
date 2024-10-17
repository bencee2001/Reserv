package hu.bme.onlabor

import hu.bme.onlabor.common.SECRET_PATH
import hu.bme.onlabor.dal.dao.user.UserDao
import hu.bme.onlabor.dal.dao.user.UserDaoImpl
import hu.bme.onlabor.enviroment.ApplicationEnv
import hu.bme.onlabor.enviroment.DevEnv
import hu.bme.onlabor.security.AuthService
import hu.bme.onlabor.security.JWTManager
import io.ktor.server.application.Application
import org.koin.dsl.module
import java.nio.file.Files
import java.nio.file.Paths

val koinAppModule = module {

    single<ApplicationEnv>{ //TODO gradle env param
        DevEnv()
    }

    single<UserDao> {
        UserDaoImpl()
    }

    single<AuthService> {
        AuthService(get())
    }

    single<JWTManager>(){
        val secret = Files.readString(Paths.get(SECRET_PATH))
        JWTManager(secret)
    }

}