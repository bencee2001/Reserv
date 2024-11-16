package hu.bme.onlabor.navigation.mainlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.bme.onlabor.model.AccommodationCardData
import hu.bme.onlabor.service.accommodation.AccommodationService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainListViewModel(
    val accommodationService: AccommodationService
): ViewModel() {

    private val _accommodations = MutableStateFlow<List<AccommodationCardData>>(emptyList())
    val accommodations = _accommodations.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.Default) {
            _accommodations.value = accommodationService.loadAccommodation()
        }
    }
}