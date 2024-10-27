package hu.bme.onlabor

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.bme.onlabor.server.ServerClient
import hu.bme.onlabor.service.AuthService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class Test(
    private val serverClient: ServerClient,
    private val authService: AuthService
): ViewModel() {


    private val _state = MutableStateFlow("")
    val state = _state.asStateFlow()

    private val _token = MutableStateFlow<String?>(null)
    val token = _token.asStateFlow()

    private val _auth = MutableStateFlow<String?>(null)
    val auth = _auth.asStateFlow()

    var username by mutableStateOf("")

    var password by mutableStateOf("")


    fun login() {
        viewModelScope.launch(Dispatchers.Default) {

        }
    }

    fun load(){
        viewModelScope.launch(Dispatchers.Default) {
            val t = serverClient.getHello()
            println(t)
        }
    }

    fun testAuth() {
        viewModelScope.launch(Dispatchers.Default) {
            val t = serverClient.testAuth(token.value!!)
            println(t)
            _auth.update {
                t
            }
        }
    }
}

