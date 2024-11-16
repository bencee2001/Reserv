package hu.bme.onlabor.ui.accommodation

import androidx.compose.runtime.Composable
import hu.bme.onlabor.model.AccommodationCardData

@Composable
expect fun  AccommodationListLayout(accommodationCardDataList: List<AccommodationCardData>)