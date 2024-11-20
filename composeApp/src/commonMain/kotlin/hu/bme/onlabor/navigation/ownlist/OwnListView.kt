package hu.bme.onlabor.navigation.ownlist

import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import hu.bme.onlabor.di.commonDI
import hu.bme.onlabor.ui.accommodation.AccommodationContentLayout
import hu.bme.onlabor.ui.accommodation.AccommodationListLayout
import hu.bme.onlabor.ui.common.AppFooter
import org.kodein.di.instance

@Composable
fun OwnListView(
    backToSearch: () -> Unit,
) {

    val ownListViewModel by commonDI.instance<OwnListViewModel>()
    val accommodations = ownListViewModel.accommodations.collectAsState().value

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
                            DropdownMenuItem(onClick = { backToSearch() }) {
                                Text("Back to Search")
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