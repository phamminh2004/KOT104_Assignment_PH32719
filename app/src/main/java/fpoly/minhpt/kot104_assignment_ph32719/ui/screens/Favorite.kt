package fpoly.minhpt.kot104_assignment_ph32719.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fpoly.minhpt.kot104_assignment_ph32719.ui.compose.FavoriteSection

@Preview(showBackground = true)
@Composable
fun Favorite() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 26.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 26.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "",
                    modifier = Modifier.size(30.dp),
                )

            }


            Text(
                text = "Favorite",
                color = Color(0xff303030),
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.CenterVertically),
                fontWeight = FontWeight.SemiBold

            )



            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Outlined.ShoppingCart,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )

            }


        }
        Box(modifier = Modifier.fillMaxSize()) {
            FavoriteSection()
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 10.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff242424)
                )
            ) {
                Text(
                    text = "Add all to my cart",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp,
                    modifier = Modifier.padding(5.dp)
                )
            }

        }
    }
}