package hu.bme.onlabor.navigation.mainlist

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import hu.bme.onlabor.navigation.ownlist.OwnListScreen

class MainListScreen: Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        MainListView(
            loginNavigate = { navigator.pop() },
            ownListNavigate = { navigator.push(OwnListScreen()) }
        )
    }
}