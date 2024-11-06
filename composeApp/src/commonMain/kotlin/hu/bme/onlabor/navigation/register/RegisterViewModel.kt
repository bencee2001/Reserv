package hu.bme.onlabor.navigation.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.bme.onlabor.commondomain.hash.cryptPassword
import hu.bme.onlabor.commondomain.validators.isValidEmail
import hu.bme.onlabor.server.AuthClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val authClient: AuthClient
): ViewModel() {

    private val _uiState = mutableStateOf(RegisterUiState())
    val uiState: State<RegisterUiState> get() = _uiState

    fun onNameChange(newName: String) {
        _uiState.value = _uiState.value.copy(name = newName)
    }

    fun onUsernameChange(newUsername: String) {
        _uiState.value = _uiState.value.copy(username = newUsername)
    }

    fun onEmailChange(newEmail: String) {
        _uiState.value = _uiState.value.copy(email = newEmail)
    }

    fun onPasswordChange(newPassword: String) {
        _uiState.value = _uiState.value.copy(password = newPassword)
    }

    fun onRePasswordChange(newRePassword: String) {
        _uiState.value = _uiState.value.copy(rePassword = newRePassword)
    }

    fun register(onSuccess: () -> Unit) {
        val name = _uiState.value.name
        val username = _uiState.value.username
        val email = _uiState.value.email
        val password = _uiState.value.password
        val rePassword = _uiState.value.rePassword
        if(name.isBlank() || username.isBlank() || password.isBlank() || email.isBlank() || rePassword.isBlank()) {
            _uiState.value = _uiState.value.copy(errorMessage = "Please fill all the above inputs.")
        }else if(!email.isValidEmail()) {
            _uiState.value = _uiState.value.copy(errorMessage = "Email format not valid.")
        } else if(password != rePassword) {
            _uiState.value = _uiState.value.copy(errorMessage = "Passwords not matching.")
        } else {
            viewModelScope.launch(Dispatchers.Default) {
                val response = authClient.register(name, username, password.cryptPassword(), email)
                when(response.status) {
                    201 -> onSuccess()
                    else -> { _uiState.value = _uiState.value.copy(errorMessage =  "Error: ${response.bodyMessage}") }
                }
            }
        }
    }
}

data class RegisterUiState(
    val name: String = "",
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val rePassword: String = "",
    val errorMessage: String? = null,
)