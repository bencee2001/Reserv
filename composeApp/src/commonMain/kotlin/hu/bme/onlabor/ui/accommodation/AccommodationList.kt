package hu.bme.onlabor.ui.accommodation

import androidx.compose.runtime.Composable
import hu.bme.onlabor.model.AccommodationCardData

@Composable
fun AccommodationList(accommodationCardDataList: List<AccommodationCardData>){
    AccommodationListLayout(accommodationCardDataList)
}