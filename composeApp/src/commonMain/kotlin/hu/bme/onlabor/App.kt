package hu.bme.onlabor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import hu.bme.onlabor.navigation.mainlist.MainListScreen
import hu.bme.onlabor.navigation.login.LoginViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kodein.di.instance

@Composable
@Preview
fun App(
) {
    val loginViewModel by commonDI.instance<LoginViewModel>()
    val uiState by loginViewModel.uiState

    val navigator = LocalNavigator.currentOrThrow

    MaterialTheme {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            TextField(
                value = uiState.username,
                onValueChange = { loginViewModel.onUsernameChange(it) },
                label = { Text("Username") }
            )
            TextField(
                value = uiState.password,
                onValueChange = { loginViewModel.onPasswordChange(it) },
                label = { Text("Password") }
            )
            Button(
                onClick = { loginViewModel.login { navigator.push(MainListScreen()) } }
            ) {
                Text("Login")
            }
            if(uiState.errorMessage != null){
                Text(
                    text = uiState.errorMessage!!
                )
            }
        }
    }
}
