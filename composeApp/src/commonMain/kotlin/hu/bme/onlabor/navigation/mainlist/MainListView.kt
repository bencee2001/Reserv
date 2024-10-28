package hu.bme.onlabor.navigation.mainlist

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import hu.bme.onlabor.ui.common.AppFooter
import hu.bme.onlabor.ui.common.AppHeader

@Composable
fun MainListView() {
    Scaffold(
        topBar = { AppHeader(false) },
        bottomBar = { AppFooter() },
        content = {}
    )
}