package fpoly.minhpt.kot104_assignment_ph32719


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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

class Checkuot : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}

@Composable
fun Checkuot(opensucset: ()-> Unit) {
    Column (modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Check out",
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 20.dp)
        )
        Row {
            Text(text = "Shipping Address", fontSize = 20.sp, color = Color(0xff909090)
            , modifier = Modifier.offset(x = (-70.dp), y = 20.dp))
            Image(painter = painterResource(id = R.drawable.edit), contentDescription ="edit",
                modifier = Modifier
                    .height(30.dp)
                    .width(30.dp)
                    .offset(x = 70.dp, y = 20.dp))
        }
        Text(text = "Bruno Fernandes", fontSize = 25.sp
            , modifier = Modifier.offset(x = (-70.dp), y = 40.dp))
        Text(text = "25 rue Robert Latouche, Nice, 06200, Côte D’azur, France", fontSize = 15.sp, color = Color(0xff909090)
            , modifier = Modifier
                .offset(x = (10.dp), y = 40.dp)
                .padding(start = 30.dp, end = 20.dp))

        Row {
            Text(text = "Payment", fontSize = 20.sp, color = Color(0xff909090)
                , modifier = Modifier.offset(x = (-110.dp), y = 70.dp))
            Image(painter = painterResource(id = R.drawable.edit), contentDescription ="edit",
                modifier = Modifier
                    .height(30.dp)
                    .width(30.dp)
                    .offset(x = 103.dp, y = 70.dp))
        }
        Image(painter = painterResource(id = R.drawable.thenganhang), contentDescription = "thenagnhang",
            modifier = Modifier
                .height(150.dp)
                .width(400.dp)
                .offset(x = 0.dp, y = 50.dp))
        Row {
            Text(text = "Delivery method", fontSize = 20.sp, color = Color(0xff909090)
                , modifier = Modifier.offset(x = (-75.dp), y = 20.dp))
            Image(painter = painterResource(id = R.drawable.edit), contentDescription ="edit",
                modifier = Modifier
                    .height(30.dp)
                    .width(30.dp)
                    .offset(x = 80.dp, y = 20.dp))

        }
        Image(painter = painterResource(id = R.drawable.thed), contentDescription = "thedhl",
            modifier = Modifier
                .height(130.dp)
                .width(390.dp)
                .offset(x = 0.dp, y = 0.dp))
        Row {
            Text(text = "Order: ", fontSize = 20.sp, color = Color(0xff909090)
                , modifier = Modifier.offset(x = (-80.dp), y = -15.dp))
            Text(text = "$ 95.00", fontSize = 20.sp
                , modifier = Modifier.offset(x = (85.dp), y = -15.dp))
        }
        Row {
            Text(text = "Delivery: ", fontSize = 20.sp, color = Color(0xff909090)
                , modifier = Modifier.offset(x = (-75.dp), y = -15.dp))
            Text(text = "$ 5.00", fontSize = 20.sp
                , modifier = Modifier.offset(x = (80.dp), y = -15.dp))
        }
        Row {
            Text(text = "Total: ", fontSize = 20.sp, color = Color(0xff909090)
                , modifier = Modifier.offset(x = (-75.dp), y = -15.dp))
            Text(text = "$ 100.00", fontSize = 20.sp
                , modifier = Modifier.offset(x = (80.dp), y = -15.dp))
        }

        Button(
            onClick = {  opensucset()},
            modifier = Modifier.padding(top = 15.dp, start = 0.dp),
            colors = ButtonDefaults.buttonColors(Color.Black), // Đặt màu nền cho nút là màu đen
            shape = MaterialTheme.shapes.small,
        ) {
            Text(
                text = "SUBMIT ORDER",
                fontSize = 30.sp,
                color = Color(0xffFFFFFF),

                )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreCheckuot(){
    Checkuot {

    }
}