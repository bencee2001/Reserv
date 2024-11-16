package hu.bme.onlabor.ui.accommodation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import hu.bme.onlabor.model.AccommodationCardData

@Composable
fun AccommodationCard(accom: AccommodationCardData){

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(32.dp),
    ) {
        Column(
            modifier = Modifier
                .background(Color(0xFF80D4ED)),
        ) {
            // Display Image
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(32.dp))
            ) {
                AsyncImage(
                    model = accom.mainImageUrl,
                    contentDescription = "Description of the image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color.LightGray),
                )
            }
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Text(
                    text = accom.name,
                    fontSize = 18.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 16.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = accom.city,
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}