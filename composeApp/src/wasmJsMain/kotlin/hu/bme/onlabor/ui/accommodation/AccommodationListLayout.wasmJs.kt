package hu.bme.onlabor.ui.accommodation

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import hu.bme.onlabor.model.AccommodationCardData

@Composable
actual fun AccommodationListLayout(accommodationCardDataList: List<AccommodationCardData>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    ) {
        items(accommodationCardDataList.size) { index ->
            AccommodationCard(accommodationCardDataList[index])
        }
    }
}