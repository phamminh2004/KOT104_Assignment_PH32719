package fpoly.minhpt.kot104_assignment_ph32719.ui.compose

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ShoppingBag
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import fpoly.minhpt.kot104_assignment_ph32719.R
import fpoly.minhpt.kot104_assignment_ph32719.Screens
import fpoly.minhpt.kot104_assignment_ph32719.data.Products


val productLists = listOf(
    Products(1, "Black Simple Lamp", 100.0, R.drawable.sp1, false),
    Products(2, "Minimal Stand", 200.0, R.drawable.sp2, false),
    Products(3, "Coffee Chair", 300.0, R.drawable.sp3, false),
    Products(4, "Simple Desk", 400.0, R.drawable.sp4, false),

    Products(5, "Black Simple Lamp", 100.0, R.drawable.sp1, false),
    Products(6, "Minimal Stand", 200.0, R.drawable.sp2, false),
    Products(7, "Coffee Chair", 300.0, R.drawable.sp3, false),
    Products(8, "Simple Desk", 400.0, R.drawable.sp4, false),


    )

val cartList = mutableListOf<Products>()


@Composable
fun ProductSection(navControl: NavHostController) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(productLists.size) { index ->
            ProductItem(index, navControl)
        }
    }
}

@Composable
fun ProductItem(index: Int, navControl: NavHostController) {

    val context = LocalContext.current

    val product = productLists[index]

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .clickable {
                Toast
                    .makeText(context, "oke", Toast.LENGTH_SHORT)
                    .show()
                navControl.navigate(Screens.ProductDetail.screen)
            }
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(5.dp) // Vertical spacing between the image and text
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
//                AsyncImage(
//                    model = product.image,
//                    contentDescription = "Product Image",
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(150.dp) // Adjust the height as needed
//                        .clip(RoundedCornerShape(10.dp)),
//                    contentScale = ContentScale.Crop
//                )

                Image(
                    painter = painterResource(id = product.image),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Fit
                )

                IconButton(
                    onClick = { cartList.add(product) },
                    modifier = Modifier
                        .padding(10.dp) // margin

                        .background(
                            Color(0xFF606060).copy(alpha = 0.5f),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(0.dp) // space between the borders
                        .align(Alignment.BottomEnd)

                        .padding(0.dp), // padding


                ) {
                    Icon(
                        imageVector = Icons.Rounded.ShoppingBag,
                        contentDescription = "",
                        Modifier
                            .size(24.dp),
                        tint = Color.White
                    )

                }

            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Column(verticalArrangement = Arrangement.Center) {
                    Text(
                        text = product.name,
                        color = Color(0xff606060),
                        fontFamily = FontFamily.Serif,
                        fontSize = 13.sp

                    )
                    Text(
                        text = "$${product.price}",
                        color = Color(0xff303030),
                        fontFamily = FontFamily.Serif,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 5.dp)
                    )

                }

            }
        }
    }
}
