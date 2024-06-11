package fpoly.minhpt.kot104_assignment_ph32719

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ass_ph31495.RetrofitBuilder
import com.example.ass_ph31495.model.ApiResponse
import com.example.ass_ph31495.model.UsersKotlin

class SingUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}

@Composable
fun SingUp(openLogin: () -> Unit) {
    Column {
        val context = LocalContext.current
        val distributors = remember { mutableStateOf(listOf<UsersKotlin>()) }
        var name by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var pass by remember { mutableStateOf("") }
        var cofimpass by remember { mutableStateOf("") }
        Column (modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally){
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

            Text(text = "WELCOME", fontSize = 35.sp, fontWeight = FontWeight.Bold,
                modifier = Modifier
//                    .align(Alignment.TopStart)
                    .padding(end = 120.dp, top = 30.dp),
                color = Color(0xff303030)
            )

            //cac nut nhap lieu
            Spacer(modifier = Modifier.height(50.dp))
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(text = "Name") })
            Spacer(modifier = Modifier.height(12.dp))
            TextField(
                value = email,
                onValueChange = { email = it },
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
            Spacer(modifier = Modifier.height(20.dp))
            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                TextField(
                    value = cofimpass,
                    onValueChange = { cofimpass = it },
                    label = { Text(text = "Conrfim Password") },
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

            Button(
                onClick = {
                    // Kiểm tra các trường nhập liệu có trống không
                    if (name.isBlank() || pass.isBlank() || email.isBlank()) {
                        Toast.makeText(context, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show()
                    } else {
                        val newDistributor = UsersKotlin(_id = "", name = name, password = pass, email = email, createdAt = "", updatedAt = "", __v = 0)
                        val retrofitService = RetrofitBuilder.getClient().create(ApiService::class.java)
                        retrofitService.signup(newDistributor).enqueue(object : Callback<ApiResponse<UsersKotlin>> {
                            override fun onResponse(call: Call<ApiResponse<UsersKotlin>>, response: Response<ApiResponse<UsersKotlin>>) {
                                if (response.isSuccessful) {
                                    val responseBody = response.body()
                                    if (responseBody != null && responseBody.status == 200) {
                                        // Add new distributor to the list
                                        val updatedDistributors = distributors.value.toMutableList()
                                        updatedDistributors.add(responseBody.data)
                                        distributors.value = updatedDistributors
                                        Log.e("zzzzzz", "asdasda")
                                        Toast.makeText(context, "Thanh công", Toast.LENGTH_SHORT).show()
                                        openLogin()
                                    } else {
                                        Log.e("zzzzzz", "v")
                                        Toast.makeText(context, "Failed to add distributor: ${responseBody?.messenger}", Toast.LENGTH_SHORT).show()
                                    }
                                } else {
                                    Log.e("zzzzzz", "3")
                                    Toast.makeText(context, "Response not successful: ${response.errorBody()}", Toast.LENGTH_SHORT).show()
                                }
                            }

                            override fun onFailure(call: Call<ApiResponse<UsersKotlin>>, t: Throwable) {
                                Log.e("zzzzzz", "5")
                                Toast.makeText(context, "No connection", Toast.LENGTH_SHORT).show()
                            }
                        })
                    }
                },
                modifier = Modifier
                    .padding(top = 50.dp)
                    .size(width = 280.dp, height = 60.dp),
                colors = ButtonDefaults.buttonColors(Color.Black), // Đặt màu nền cho nút là màu đen
                shape = MaterialTheme.shapes.small,
            ) {
                Text(text = "LOGIN")
            }

//            Button(onClick = {
//                val retrofitService = RetrofitBuilder.getClient().create(ApiService::class.java)
//                retrofitService.getListUser().enqueue(object : Callback<ApiResponse<List<UsersKotlin>>> {
//                    override fun onResponse(call: Call<ApiResponse<List<UsersKotlin>>>, response: Response<ApiResponse<List<UsersKotlin>>>) {
//                        if (response.isSuccessful) {
//                            val responseBody = response.body()
//                            if (responseBody != null && responseBody.status == 200) {
//                                distributors.value = responseBody.data
////                            Toast.makeText(this@MainActivity, "Fetched distributors successfully", Toast.LENGTH_SHORT).show()
//                            } else {
////                            Toast.makeText(this@MainActivity, "Failed to fetch distributors: ${responseBody?.messenger}", Toast.LENGTH_SHORT).show()
//                            }
//                        } else {
////                        Toast.makeText(this@MainActivity, "Response not successful: ${response.errorBody()}", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//
//                    override fun onFailure(call: Call<ApiResponse<List<UsersKotlin>>>, t: Throwable) {
////                    Toast.makeText(this@MainActivity, "No connection", Toast.LENGTH_SHORT).show()
//                    }
//                })
//            }) {
//                Text(text = "Fetch Distributors")
//            }
            PostList(distributors.value)

            Row (verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Already have account?" , fontSize = 20.sp,
                    modifier = Modifier.padding(top = 30.dp))
                Text(text = "LOG IN",fontSize = 20.sp, fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .clickable { openLogin() })
            }
        }



    }
}

@Composable
fun PostList(distributors : List<UsersKotlin>){
    LazyColumn {
       items(distributors) {
           distributors -> Text(text = distributors.name)
       }
    }
}
