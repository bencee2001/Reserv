package hu.bme.onlabor.navigation.mainlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hu.bme.onlabor.model.AccommodationCardData
import hu.bme.onlabor.ui.accommodation.AccommodationList
import hu.bme.onlabor.ui.accommodation.AccommodationListLayout
import hu.bme.onlabor.ui.common.AppFooter
import hu.bme.onlabor.ui.common.AppHeader
import kotlin.contracts.contract

@Composable
fun MainListView(
    loginNavigate: () -> Unit,
) {
    MainListLayout(
        appHeader = {
            AppHeader(false) {
                Button(
                    onClick = { loginNavigate() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("To Login")
                }
            }
        },
        list = {
            AccommodationList(
                listOf(
                    AccommodationCardData(),
                    AccommodationCardData(),
                    AccommodationCardData(),
                    AccommodationCardData(),
                    AccommodationCardData(),
                    AccommodationCardData(),
                    AccommodationCardData(),
                    AccommodationCardData()
                )
            )
        },
        appFooter = {
            AppFooter()
        }
    )
}