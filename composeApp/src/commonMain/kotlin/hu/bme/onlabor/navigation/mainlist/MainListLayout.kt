package hu.bme.onlabor.navigation.mainlist

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable

@Composable
expect fun MainListLayout(
    appHeader: @Composable () -> Unit,
    list: @Composable () -> Unit,
    appFooter: @Composable () -> Unit,
)