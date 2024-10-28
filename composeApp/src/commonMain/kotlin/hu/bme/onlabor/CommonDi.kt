package hu.bme.onlabor

import hu.bme.onlabor.server.ServerClient
import hu.bme.onlabor.server.getServerClient
import hu.bme.onlabor.service.AuthService
import hu.bme.onlabor.navigation.login.LoginViewModel
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val commonDI = DI {
    bind<Test>() with singleton { Test(getServerClient(), instance()) }

    bind<AuthService>() with singleton { AuthService() }

    bind<LoginViewModel>() with singleton { LoginViewModel(instance(), instance()) }

    bind<ServerClient>() with singleton { getServerClient() }
}
