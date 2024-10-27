package hu.bme.onlabor.navigation.mainlist

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import hu.bme.onlabor.navigation.login.LoginScreen

class MainListScreen: Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Text(
            text = "List"
        )
        Button(
            onClick = {
                navigator.push(LoginScreen())
            }
        ) {
            Text("To Login")
        }
    }
}