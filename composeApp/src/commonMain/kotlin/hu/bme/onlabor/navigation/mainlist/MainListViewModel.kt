package hu.bme.onlabor.navigation.mainlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.bme.onlabor.model.AccommodationCardData
import hu.bme.onlabor.service.accommodation.AccommodationService
import hu.bme.onlabor.service.auth.AuthService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainListViewModel(
    private val accommodationService: AccommodationService,
    private val authService: AuthService
): ViewModel() {

    private val _accommodations = MutableStateFlow<List<AccommodationCardData>>(emptyList())
    val accommodations = _accommodations.asStateFlow()
    val userRole = authService.getRole()

    init {
        viewModelScope.launch(Dispatchers.Default) {
            _accommodations.value = accommodationService.loadAccommodation()
        }
    }

    fun logout(toLogin: () -> Unit) {
        authService.clear()
        toLogin()
    }
}