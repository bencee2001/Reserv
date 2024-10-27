package hu.bme.onlabor

import hu.bme.onlabor.server.getServerClient
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

val commonDI = DI {
    bind<Test>() with singleton { Test(getServerClient()) }
}
