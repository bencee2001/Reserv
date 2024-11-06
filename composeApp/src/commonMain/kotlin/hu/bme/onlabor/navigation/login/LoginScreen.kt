package hu.bme.onlabor.navigation.login

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import hu.bme.onlabor.navigation.mainlist.MainListScreen
import hu.bme.onlabor.navigation.register.RegisterScreen


class LoginScreen: Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        LoginView(
            loginNavigate = { navigator.push(MainListScreen()) },
            registerNavigate = { navigator.push(RegisterScreen())}
        )
    }
}