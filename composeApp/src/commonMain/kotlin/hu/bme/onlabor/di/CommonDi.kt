package hu.bme.onlabor.di

import hu.bme.onlabor.server.ServerClient
import hu.bme.onlabor.server.getServerClient
import hu.bme.onlabor.service.AuthService
import hu.bme.onlabor.navigation.login.LoginViewModel
import hu.bme.onlabor.navigation.register.RegisterViewModel
import hu.bme.onlabor.server.AuthClient
import org.kodein.di.*

val commonDI = DI {
    bind<AuthService>() with singleton { AuthService() }

    bind<LoginViewModel>() with factory { LoginViewModel(instance(), instance()) }

    bind<RegisterViewModel>() with singleton { RegisterViewModel(instance()) }

    bind<ServerClient>() with singleton { getServerClient() }

    bind<AuthClient>() with singleton { getServerClient() }
}
