package fpoly.minhpt.kot104_assignment_ph32719


import android.content.Context
import android.os.Bundle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.RemoteViews.RemoteView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ass_ph31495.RetrofitBuilder
import com.example.ass_ph31495.model.ApiResponse
import com.example.ass_ph31495.model.UsersKotlin

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}


@Composable
fun Login (openSignup:()-> Unit,
           openDSSP:()-> Unit) {
    Column {
        val context = LocalContext.current
        var userName by remember { mutableStateOf("") }
        var pass by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            //logo
            Row(verticalAlignment = Alignment.CenterVertically){

                Image(
                    painter = painterResource(id = R.drawable.thanhke),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .padding(end = 20.dp, top = 30.dp)
                        .size(90.dp)

                )
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .size(70.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.thanhke),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .padding(start = 20.dp, top = 30.dp)
                        .size(90.dp)

                )

            }

            Text(text = "Hello !", fontSize = 35.sp,
                modifier = Modifier
//                    .align(Alignment.TopStart)
                    .padding(end = 210.dp, top = 25.dp),
                color = Color(0xff606060)
            )
            Text(text = "WELCOME BACK", fontSize = 30.sp, fontWeight = FontWeight.Bold,
                modifier = Modifier
//                    .align(Alignment.TopStart)
                    .padding(end = 80.dp),
                color = Color(0xff303030)
            )
            Spacer(modifier = Modifier.height(50.dp))
            TextField(
                value = userName,
                onValueChange = { userName = it },
                label = { Text(text = "Email") })
            Spacer(modifier = Modifier.height(12.dp))
            // Hàng chứa ô password và hình ảnh
            // Box chứa ô password và hình ảnh
            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                TextField(
                    value = pass,
                    onValueChange = { pass = it },
                    label = { Text(text = "Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    // Đặt trọng số của ô password là 1 để nó chiếm phần lớn trong Box
                )
                // Hình ảnh
                Image(
                    painter = painterResource(id = R.drawable.hinhmat),
                    contentDescription = "hinhmat",
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.CenterEnd) // Đặt hình ảnh ở góc phải của Box
                        .padding(end = 16.dp) // Khoảng cách giữa hình ảnh và ô password
                )
            }
            Text(text = "Forgot Password", fontSize = 20.sp,
                modifier = Modifier
//                    .align(Alignment.TopStart)
                    .padding(start = 10.dp, top = 30.dp),
                color = Color(0xff303030)
            )
            Button(onClick = {
                if (userName.isNotEmpty() && pass.isNotEmpty()) {
                    val user = UsersKotlin("","",pass,userName,"","",0) // Khởi tạo đối tượng UsersKotlin với userName và pass
                    login(user, context, openDSSP)
                } else {
                    Toast.makeText(context, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show()
                }
                             },
                modifier = Modifier
                    .padding(top = 50.dp)
                    .size(width = 280.dp, height = 60.dp),
                colors = ButtonDefaults.buttonColors(Color.Black), // Đặt màu nền cho nút là màu đen
                shape = MaterialTheme.shapes.small,
            ) {
                Text(text = "Login",
                    fontSize = 30.sp,
                    color = Color(0xffFFFFFF),

                    )
            }
            Text(text = "SIGN UP", fontSize = 28.sp,
                modifier = Modifier
//                    .align(Alignment.TopStart)
                    .padding(start = 10.dp, top = 25.dp)
                    .clickable { openSignup() },
                color = Color(0xff303030)
            )

        }
    }
}



private fun login(user: UsersKotlin, context: Context, openDSSP: () -> Unit) {
    val retrofitService = RetrofitBuilder.getClient().create(ApiService::class.java)
    retrofitService.login(user).enqueue(object : Callback<ApiResponse<UsersKotlin>> {
        override fun onResponse(call: Call<ApiResponse<UsersKotlin>>, response: Response<ApiResponse<UsersKotlin>>) {
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && responseBody.status == 200) {
                    Toast.makeText(context, "Đăng nhập thành công", Toast.LENGTH_SHORT).show()
                    openDSSP()
                } else {
                    Toast.makeText(context, "Đăng nhập không thành công: ${responseBody?.messenger}", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Response not successful: ${response.errorBody()}", Toast.LENGTH_SHORT).show()
            }
        }

        override fun onFailure(call: Call<ApiResponse<UsersKotlin>>, t: Throwable) {
            Toast.makeText(context, "No connection", Toast.LENGTH_SHORT).show()
        }
    })
}

