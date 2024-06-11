package fpoly.minhpt.kot104_assignment_ph32719

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Sucset : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
//@Preview(showBackground = true)
@Composable
fun Sucset(openhome: () -> Unit) {
    Column (modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = "SUCCESS!", fontSize = 40.sp, fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 30.dp))
        Box(
            modifier = Modifier
                .height(180.dp)
                .width(180.dp)
                .offset(y = 50.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.khung),
                contentDescription = "Foreground Image",
                modifier = Modifier
                    .align(Alignment.Center)
                    .height(200.dp)
                    .width(200.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.anhnen),
                contentDescription = "Background Image",
                modifier = Modifier
                    .fillMaxSize()
            )
            Image(
                painter = painterResource(id = R.drawable.dautich),
                contentDescription = "Foreground Image",
                modifier = Modifier
                    .align(Alignment.Center)
                    .height(40.dp)
                    .width(40.dp)
                    .offset(x = 0.dp, y = 90.dp)
            )
        }
        Text(text = "Your order will be delivered soon.\n" +
                "Thank you for choosing our app!", fontSize = 20.sp, color = Color(0xff909090)
            , modifier = Modifier.offset( y = 110.dp))
        Button(
            onClick = {  },
            modifier = Modifier.padding(top = 170.dp, start = 0.dp).height(50.dp).width(300.dp),
            colors = ButtonDefaults.buttonColors(Color.Black), // Đặt màu nền cho nút là màu đen
            shape = MaterialTheme.shapes.small,
        ) {
            Text(
                text = "Track your orders",
                fontSize = 20.sp,
                color = Color(0xffFFFFFF),

                )
        }
        Button(
            onClick = { openhome() },
            modifier = Modifier.padding(top = 50.dp, start = 0.dp).height(50.dp).width(300.dp),
            colors = ButtonDefaults.buttonColors(Color.White), // Đặt màu nền cho nút là màu đen
            shape = MaterialTheme.shapes.small,
        ) {
            Text(
                text = "BACK TO HOME",
                fontSize = 20.sp,
                color = Color(0xff000000),

                )
        }
    }
}