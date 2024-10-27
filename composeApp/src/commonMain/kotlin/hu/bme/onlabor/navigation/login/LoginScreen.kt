package hu.bme.onlabor.navigation.login

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import hu.bme.onlabor.navigation.mainlist.MainListScreen

class LoginScreen: Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Text(
            text = "Hope it is good for both side"
        )
        Button(
            onClick = {
                navigator.push(MainListScreen())
            }
        ) {
            Text("To List")
        }
    }
}