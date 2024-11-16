package hu.bme.onlabor.navigation.mainlist

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
actual fun MainListLayout(
    appHeader: @Composable () -> Unit,
    list: @Composable () -> Unit,
    appFooter: @Composable () -> Unit
) {
    Scaffold(
        topBar = { appHeader() },
        bottomBar = { appFooter() },
        content = { padding ->
            Column(modifier = Modifier.fillMaxSize().padding(padding)) {
                list()
            }
        }
    )
}