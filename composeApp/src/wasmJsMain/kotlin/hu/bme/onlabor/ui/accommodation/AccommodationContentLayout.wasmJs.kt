package hu.bme.onlabor.ui.accommodation

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
actual fun AccommodationContentLayout(
    padding: PaddingValues,
    accommodationList: @Composable () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
    ) {
        Column(Modifier.weight(0.15f).fillMaxHeight()) {}
        Column(Modifier.weight(0.7f).fillMaxHeight()){ accommodationList() }
        Column(Modifier.weight(0.15f).fillMaxHeight()) {}
    }
}