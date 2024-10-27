package hu.bme.onlabor.viewmodel.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.bme.onlabor.server.ServerClient
import hu.bme.onlabor.service.AuthService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authService: AuthService,
    private val serverClient: ServerClient
): ViewModel() {

    private val _uiState = mutableStateOf(LoginUiState())
    val uiState: State<LoginUiState> get() = _uiState

    fun onUsernameChange(newUsername: String) {
        _uiState.value = _uiState.value.copy(username = newUsername)
    }

    fun onPasswordChange(newPassword: String) {
        _uiState.value = _uiState.value.copy(password = newPassword)
    }

    fun login(navigateTo: () -> Unit) {
        val username = _uiState.value.username
        val password = _uiState.value.password
        if(username.isNotBlank() && password.isNotBlank()){
            viewModelScope.launch(Dispatchers.Default) {
                val response = serverClient.login(username, password)
                when (response.status) {
                    200 -> {
                        authService.setAuthToken(response.bodyMessage)
                        navigateTo()
                    }
                    else -> { _uiState.value = _uiState.value.copy(errorMessage =  response.bodyMessage)}
                }
            }
        } else {
            _uiState.value = _uiState.value.copy(errorMessage = "Please fill all the above inputs.")
        }

    }
}

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val errorMessage: String? = null,
    val isLoggedIn: Boolean = false
)