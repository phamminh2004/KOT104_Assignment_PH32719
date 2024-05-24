package fpoly.minhpt.kot104_assignment_ph32719.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.rounded.ShoppingBag
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fpoly.minhpt.kot104_assignment_ph32719.R
import fpoly.minhpt.kot104_assignment_ph32719.data.Products


val productList = listOf(
    Products(1, "Black Simple Lamp", 100.0, R.drawable.sp1, false),
    Products(2, "Minimal Stand", 200.0, R.drawable.sp2, false),
    Products(3, "Coffee Chair", 300.0, R.drawable.sp3, false),
    Products(4, "Simple Desk", 400.0, R.drawable.sp4, false),

    Products(5, "Black Simple Lamp", 100.0, R.drawable.sp1, false),
    Products(6, "Minimal Stand", 200.0, R.drawable.sp2, false),
    Products(7, "Coffee Chair", 300.0, R.drawable.sp3, false),
    Products(8, "Simple Desk", 400.0, R.drawable.sp4, false),

    )

@Preview(showBackground = true)
@Composable
fun FavoriteSection() {
    LazyColumn(
        modifier = Modifier
            .padding(bottom = 40.dp)
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(productList.size) { index ->
            ItemFavorite(index = index)
            if (index < productList.size - 1) {
                Divider(
                    color = Color(0xffF0F0F0),
                    thickness = 1.7.dp,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }

        }
    }
}

@Composable
fun ItemFavorite(index: Int) {
    val item = productList[index]
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = item.image),
            contentDescription = null,
            modifier = Modifier

                .fillMaxWidth(0.2f)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Fit,
        )
        Column(
            Modifier.fillMaxWidth(0.7f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = item.name,
                Modifier.padding(bottom = 5.dp),
                color = Color(0xff606060),
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "$" + item.price,
                fontWeight = FontWeight(590),
                fontFamily = FontFamily.Serif
            )
        }

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Outlined.Cancel, contentDescription = "", Modifier.size(24.dp))

            }



            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(48.dp)
                    .padding(5.dp)
                    .background(Color(0xffE0E0E0), shape = RoundedCornerShape(10.dp))
                    .padding(top = 0.dp)
            ) {
                Icon(
                    Icons.Rounded.ShoppingBag, contentDescription = "", Modifier.size(24.dp)
                )

            }

        }
    }
}