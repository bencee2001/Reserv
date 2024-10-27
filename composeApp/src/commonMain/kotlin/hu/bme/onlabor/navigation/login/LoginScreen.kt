package hu.bme.onlabor.navigation.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import hu.bme.onlabor.commonDI
import hu.bme.onlabor.navigation.mainlist.MainListScreen
import hu.bme.onlabor.viewmodel.login.LoginViewModel
import org.kodein.di.instance

class LoginScreen: Screen {

    @Composable
    override fun Content() {
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
}