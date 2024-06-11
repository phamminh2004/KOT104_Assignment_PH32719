package fpoly.minhpt.kot104_assignment_ph32719


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ass_ph31495.RetrofitBuilder
import com.example.ass_ph31495.model.ApiResponse
import com.example.ass_ph31495.model.SanphamKotlin
import com.example.ass_ph31495.model.UsersKotlin
import com.example.ass_ph31495.model.giohang
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Chitietsanpham : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
        }
    }
}

@Composable
fun Manchitiet(openCart: ()-> Unit,productId: String) {

    val coroutineScope = rememberCoroutineScope()
    val product = remember { mutableStateOf<SanphamKotlin?>(null) }
    val quantity = remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        coroutineScope.launch(Dispatchers.IO) {
            val retrofitService = RetrofitBuilder.getClient().create(ApiService::class.java)
            retrofitService.getProductKById(productId).enqueue(object :
                Callback<ApiResponse<SanphamKotlin>> {
                override fun onResponse(
                    call: Call<ApiResponse<SanphamKotlin>>,
                    response: Response<ApiResponse<SanphamKotlin>>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null && responseBody.status == 200) {
                            product.value = responseBody.data
                        } else {
                            // Handle error
                        }
                    } else {
                        // Handle error
                    }
                }
                override fun onFailure(call: Call<ApiResponse<SanphamKotlin>>, t: Throwable) {
                    // Handle failure
                }
            })
        }
    }
    product.value?.let { productData ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 20.dp)) { // Thêm khoảng cách dưới cột chính
            Box(modifier = Modifier.weight(1f)) {
                AsyncImage(
                    model = productData.image,
                    contentDescription = "Product Image",
                    modifier = Modifier
                        .heightIn(600.dp)
                        .width(600.dp)
                        .padding(start = 60.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.khungmau),
                    contentDescription = "khungmau",
                    modifier = Modifier
                        .heightIn(230.dp)
                        .width(180.dp)
                        .align(Alignment.CenterStart)
                        .padding(end = 10.dp)
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 20.dp)
            ) {
                Text(
                    text = productData.name,
                    fontSize = 30.sp,
                    modifier = Modifier.padding(top = 20.dp)
                )

                Row {
                    Text(
                        text = "$ ${productData.price}",
                        fontSize = 30.sp,
                        modifier = Modifier.padding(top = 5.dp),
                        fontWeight = FontWeight.Bold
                    )

                    Image(
                        painter = painterResource(id = R.drawable.tru1),
                        contentDescription = "tru1",
                        modifier = Modifier
                            .height(50.dp)
                            .width(40.dp)
                            .offset(x = 96.dp)
                            .clickable {
                                if (quantity.value > 0) {
                                    quantity.value -= 1 // Decrement quantity
                                }
                            }
                    )
                    Text(
                        text = quantity.value.toString(),
                        fontSize = 30.sp,
                        modifier = Modifier.padding(top = 5.dp, start = 120.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.cong),
                        contentDescription = "cong",
                        modifier = Modifier
                            .height(50.dp)
                            .width(40.dp)
                            .offset(x = 16.dp)
                            .clickable {
                                quantity.value += 1 // Increment quantity
                            }
                    )
                }

                Row {
                    Image(
                        painter = painterResource(id = R.drawable.saodanhgia),
                        contentDescription = "danhgia",
                        modifier = Modifier
                            .height(30.dp)
                            .width(30.dp)
                    )
                    Text(
                        text = productData.rate.toString(),
                        fontSize = 25.sp,
                        modifier = Modifier.padding(start = 10.dp, top = 0.dp)
                    )
                    Text(
                        text = "(50 reviews)",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(start = 30.dp, top = 0.dp),
                        color = Color(0xff808080)
                    )
                }

                Text(
                    text = "${productData.mota}",
                    fontSize = 18.sp,
                    color = Color(0xff808080),
                    modifier = Modifier.padding(end = 10.dp).height(100.dp)
                )

                Row {
                    Image(
                        painter = painterResource(id = R.drawable.danhdau),
                        contentDescription = "danhdau",
                        modifier = Modifier
                            .height(60.dp)
                            .width(70.dp)
                    )
                    Button(
                        onClick = {
                            val cart = product.value?.let {
                                giohang(
                                    _id = "",
                                    image = it.image,
                                    name = it.name,
                                    price = it.price,
                                    quantity = quantity.value,
                                    createdAt = "",
                                    updatedAt = "",
                                    __v = 0
                                )
                            }
                            val retrofitService = RetrofitBuilder.getClient().create(ApiService::class.java)
                            if (cart != null) {
                                retrofitService.bills(cart).enqueue(object :
                                    Callback<ApiResponse<giohang>> {
                                    override fun onResponse(call: Call<ApiResponse<giohang>>, response: Response<ApiResponse<giohang>>) {
                                        if (response.isSuccessful) {
                                            val responseBody = response.body()
                                            if (responseBody != null && responseBody.status == 200) {
                                                openCart() // Navigate to cart screen
                                            } else {
                                                // Handle failure to add to cart
                                            }
                                        } else {
                                            // Handle response error
                                        }
                                    }

                                    override fun onFailure(call: Call<ApiResponse<giohang>>, t: Throwable) {
                                        // Handle failure
                                    }
                                })
                            }


                            openCart() },
                        modifier = Modifier
                            .size(width = 250.dp, height = 60.dp),
                        colors = ButtonDefaults.buttonColors(Color.Black),
                        shape = MaterialTheme.shapes.small,
                    ) {
                        Text(
                            text = "Add to cart",
                            fontSize = 23.sp,
                            color = Color(0xffFFFFFF),
                        )
                    }
                }
            }
        }
    } ?: run {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Loading...", fontSize = 20.sp)
        }
    }
}
