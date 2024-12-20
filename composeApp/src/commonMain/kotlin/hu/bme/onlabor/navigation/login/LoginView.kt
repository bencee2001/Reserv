package hu.bme.onlabor.navigation.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import hu.bme.onlabor.di.commonDI
import hu.bme.onlabor.ui.common.AppFooter
import hu.bme.onlabor.ui.common.AppHeader
import org.kodein.di.instance

@Composable
fun LoginView(
    loginNavigate: () -> Unit,
    registerNavigate: () -> Unit
) {
    val loginViewModel by commonDI.instance<LoginViewModel>()
    val uiState by loginViewModel.uiState

    Scaffold(
        topBar =  {
            AppHeader(
                showUserIcon = false,
                rightSide = {
                    TextButton(onClick = { registerNavigate() }) {
                        Text("Register", color = Color.White)
                    }
                }
            )
        },
        bottomBar = {
            AppFooter()
        },
        content = { paddingValues -> //TODO expect-actual
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
                        label = { Text("Password") },
                        visualTransformation = PasswordVisualTransformation()
                    )
                    Button(
                        onClick = { loginViewModel.login { loginNavigate() } }
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
    )
}