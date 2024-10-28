package hu.bme.onlabor.ui.common

import androidx.compose.material.BottomAppBar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun AppFooter() {
    BottomAppBar(
        content = {
            Text("Footer Content")
        }
    )
}