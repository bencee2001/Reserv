package hu.bme.onlabor.navigation.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import hu.bme.onlabor.di.commonDI
import hu.bme.onlabor.ui.common.AppFooter
import hu.bme.onlabor.ui.common.AppHeader
import kotlinx.coroutines.launch
import org.kodein.di.instance

@Composable
fun RegisterView(
    backToLoginNavigate: () -> Unit,
) {

    val registerViewModel by commonDI.instance<RegisterViewModel>()
    val uiState by registerViewModel.uiState
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar =  {
            AppHeader(
                showUserIcon = false,
                rightSide = {
                    TextButton(onClick = { backToLoginNavigate() }) {
                        Text("Login", color = Color.White)
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
                        value = uiState.name,
                        onValueChange = { registerViewModel.onNameChange(it) },
                        label = { Text("Name") }
                    )
                    TextField(
                        value = uiState.username,
                        onValueChange = { registerViewModel.onUsernameChange(it) },
                        label = { Text("Username") }
                    )
                    TextField(
                        value = uiState.email,
                        onValueChange = { registerViewModel.onEmailChange(it) },
                        label = { Text("Email") }
                    )
                    TextField(
                        value = uiState.password,
                        onValueChange = { registerViewModel.onPasswordChange(it) },
                        label = { Text("Password") },
                        visualTransformation = PasswordVisualTransformation()
                    )
                    TextField(
                        value = uiState.rePassword,
                        onValueChange = { registerViewModel.onRePasswordChange(it) },
                        label = { Text("RePassword") },
                        visualTransformation = PasswordVisualTransformation()
                    )
                    Button(
                        onClick = {
                            registerViewModel.register {
                                coroutineScope.launch {
                                    snackBarHostState.showSnackbar("Registration successful!")
                                }
                                backToLoginNavigate()
                            }
                        }
                    ) {
                        Text("Register")
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