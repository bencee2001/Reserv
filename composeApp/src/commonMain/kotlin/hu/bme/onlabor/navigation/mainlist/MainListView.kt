package hu.bme.onlabor.navigation.mainlist

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hu.bme.onlabor.di.commonDI
import hu.bme.onlabor.model.AccommodationCardData
import hu.bme.onlabor.ui.accommodation.AccommodationContentLayout
import hu.bme.onlabor.ui.accommodation.AccommodationListLayout
import hu.bme.onlabor.ui.common.AppFooter
import hu.bme.onlabor.ui.common.AppHeader
import hu.bme.onlabor.util.AuthUtil
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.kodein.di.instance
import kotlin.contracts.contract

@Composable
fun MainListView(
    loginNavigate: () -> Unit,
    ownListNavigate: () -> Unit,
) {
    val mainListViewModel by commonDI.instance<MainListViewModel>()
    val accommodations = mainListViewModel.accommodations.collectAsState().value

    var isMenuExpanded by remember { mutableStateOf(false) }

    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = "Accommodation List") },
                actions = {
                    Box {
                        IconButton(onClick = { isMenuExpanded = !isMenuExpanded }) {
                            Icon(Icons.Filled.Menu, contentDescription = "Menu")
                        }
                        DropdownMenu(
                            expanded = isMenuExpanded,
                            onDismissRequest = { isMenuExpanded = false }
                        ) {
                            if(AuthUtil.isNotSimpleUser(mainListViewModel.userRole)) {
                                DropdownMenuItem(onClick = { ownListNavigate() }) {
                                    Text("Own Accommodations")
                                }
                            }
                            DropdownMenuItem(onClick = { mainListViewModel.logout { loginNavigate() } }) {
                                Text("Logout")
                            }
                        }
                    }
                },
            )
        },
        bottomBar = {
            AppFooter()
        }
    ) { paddingValues ->
        AccommodationContentLayout(paddingValues) {
            AccommodationListLayout(accommodations)
        }
    }
}