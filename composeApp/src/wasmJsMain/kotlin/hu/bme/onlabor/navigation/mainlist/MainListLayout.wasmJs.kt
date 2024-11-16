package hu.bme.onlabor.navigation.mainlist

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
            ) {
                Column(Modifier.weight(0.15f).fillMaxHeight()) {}
                Column(Modifier.weight(0.7f).fillMaxHeight()){ list() }
                Column(Modifier.weight(0.15f).fillMaxHeight()) {}
            }
        }
    )
}