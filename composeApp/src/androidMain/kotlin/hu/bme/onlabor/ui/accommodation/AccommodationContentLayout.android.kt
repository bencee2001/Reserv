package hu.bme.onlabor.ui.accommodation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
actual fun AccommodationContentLayout(
    padding: PaddingValues,
    accommodationList: @Composable () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(padding)) {
        accommodationList()
    }
}