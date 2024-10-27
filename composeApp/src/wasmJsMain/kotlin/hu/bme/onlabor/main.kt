package hu.bme.onlabor

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import cafe.adriel.voyager.navigator.Navigator
import hu.bme.onlabor.navigation.login.LoginScreen

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(viewportContainerId = "composeApplication") {
        Navigator(LoginScreen())
    }
}