package hu.bme.onlabor

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import hu.bme.onlabor.navigation.login.LoginScreen
import hu.bme.onlabor.navigation.mainlist.MainListScreen
import hu.bme.onlabor.viewmodel.login.LoginViewModel
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kodein.di.instance
import reserv.composeapp.generated.resources.Res
import reserv.composeapp.generated.resources.compose_multiplatform

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
