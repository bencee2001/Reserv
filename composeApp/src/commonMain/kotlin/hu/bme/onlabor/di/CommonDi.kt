package hu.bme.onlabor.di

import hu.bme.onlabor.server.ServerClient
import hu.bme.onlabor.server.getServerClient
import hu.bme.onlabor.service.auth.AuthService
import hu.bme.onlabor.navigation.login.LoginViewModel
import hu.bme.onlabor.navigation.mainlist.MainListViewModel
import hu.bme.onlabor.navigation.ownlist.OwnListViewModel
import hu.bme.onlabor.navigation.register.RegisterViewModel
import hu.bme.onlabor.server.AccommodationClient
import hu.bme.onlabor.server.AuthClient
import hu.bme.onlabor.service.accommodation.AccommodationService
import org.kodein.di.*

val commonDI = DI {
    bind<AuthService>() with singleton { AuthService() }
    bind<AccommodationService>() with singleton { AccommodationService(instance(), instance()) }

    bind<LoginViewModel>() with factory { LoginViewModel(instance(), instance()) }
    bind<RegisterViewModel>() with singleton { RegisterViewModel(instance()) }
    bind<MainListViewModel>() with singleton { MainListViewModel(instance(), instance()) }
    bind<OwnListViewModel>() with singleton { OwnListViewModel(instance()) }

    bind<ServerClient>() with singleton { getServerClient() }
    bind<AuthClient>() with singleton { getServerClient() }
    bind<AccommodationClient>() with singleton { getServerClient() }
}
