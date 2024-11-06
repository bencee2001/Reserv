package hu.bme.onlabor.navigation.register

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

class RegisterScreen: Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        RegisterView(
            backToLoginNavigate = { navigator.pop() }
        )
    }
}