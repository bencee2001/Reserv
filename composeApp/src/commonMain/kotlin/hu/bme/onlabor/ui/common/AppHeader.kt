package hu.bme.onlabor.ui.common

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun AppHeader(
    showUserIcon: Boolean
) {
    TopAppBar(
        title = { Text(text = "Header Title") },
    )
}