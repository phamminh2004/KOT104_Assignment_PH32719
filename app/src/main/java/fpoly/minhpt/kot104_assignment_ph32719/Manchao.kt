package fpoly.minhpt.kot104_assignment_ph32719


import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

    @Composable
    fun ManChao(openLoginScreen: () -> Unit) {

        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.hinhnen), contentDescription = "Anhnen",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Text(
                text = "MAKE YOUR", fontSize = 30.sp,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 30.dp, top = 220.dp),
                color = Color(0xff606060)
            )
            Text(
                text = "HOME BEAUTIFUL", fontSize = 35.sp, fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 30.dp, top = 285.dp),
                color = Color(0xff303030)
            )
            Text(
                text = "The best simple place where you discover most wonderful furnitures and make your home beautiful",
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(start = 50.dp, end = 15.dp),
                color = Color(0xff808080),

                )
            Button(
                onClick = { openLoginScreen() },
                modifier = Modifier.padding(top = 600.dp, start = 100.dp),
                colors = ButtonDefaults.buttonColors(Color.Black), // Đặt màu nền cho nút là màu đen
                shape = MaterialTheme.shapes.small,
            ) {
                Text(
                    text = "Get Started",
                    fontSize = 30.sp,
                    color = Color(0xffFFFFFF),

                    )
            }

        }
    }

//@Preview(showBackground = true)
//@Composable
//fun PreviewManChao() {
//    ManChao {
//        // This is a preview, no action needed here
//    }
//}