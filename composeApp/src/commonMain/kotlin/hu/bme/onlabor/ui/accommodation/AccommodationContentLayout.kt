package hu.bme.onlabor.ui.accommodation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable

@Composable
expect fun AccommodationContentLayout(padding: PaddingValues, accommodationList: @Composable () -> Unit)