package hu.bme.onlabor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.bme.onlabor.expect.makeKtorCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class Test(): ViewModel() {

    private val _state = MutableStateFlow("")
    val state = _state.asStateFlow()

    init {
        load()
    }

    private fun load(){
        viewModelScope.launch(Dispatchers.Default) {
            _state.update {
                makeKtorCall()
            }
        }
    }
}