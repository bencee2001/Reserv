package hu.bme.onlabor.ui.accommodation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import hu.bme.onlabor.model.AccommodationCardData

@Composable
actual fun AccommodationListLayout(accommodationCardDataList: List<AccommodationCardData>) {
    LazyColumn {
        items(accommodationCardDataList.size) { index ->
            AccommodationCard(accommodationCardDataList[index])
        }
    }
}