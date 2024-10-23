package hu.bme.onlabor

import hu.bme.onlabor.common.SECRET_PATH
import hu.bme.onlabor.dal.dao.user.UserDao
import hu.bme.onlabor.dal.dao.user.UserDaoImpl
import hu.bme.onlabor.enviroment.ApplicationEnv
import hu.bme.onlabor.enviroment.DevEnv
import hu.bme.onlabor.security.AuthService
import hu.bme.onlabor.security.JWTManager
import org.koin.dsl.module
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.NoSuchFileException

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
        val secret = try {
            Files.readString(Paths.get(SECRET_PATH))
        }catch (e: Exception) {
            println()
            throw NoSuchFileException("$SECRET_PATH file not found.")
        }
        JWTManager(secret)
    }

}