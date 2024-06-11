package fpoly.minhpt.kot104_assignment_ph32719


import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ass_ph31495.model.ApiResponse
import com.example.ass_ph31495.model.giohang
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.ass_ph31495.RetrofitBuilder

class MycarActyvitiy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}

@Composable
fun Cart(openCheckout: () -> Unit) {
    val context = LocalContext.current
    val products = remember { mutableStateOf(listOf<giohang>()) }
    val coroutineScope = rememberCoroutineScope()
    val totalPrice = remember { mutableStateOf(0.0) }

    LaunchedEffect(Unit) {
        coroutineScope.launch(Dispatchers.IO) {
            val retrofitService = RetrofitBuilder.getClient().create(ApiService::class.java)
            retrofitService.getbill().enqueue(object : Callback<ApiResponse<List<giohang>>> {
                override fun onResponse(call: Call<ApiResponse<List<giohang>>>, response: Response<ApiResponse<List<giohang>>>) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null && responseBody.status == 200) {
                            products.value = responseBody.data
                            Toast.makeText(context, "Fetched products successfully", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Failed to fetch products: ${responseBody?.messenger}", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(context, "Response not successful: ${response.errorBody()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ApiResponse<List<giohang>>>, t: Throwable) {
                    Toast.makeText(context, "No connection", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    val deleteProduct: (giohang) -> Unit = { product ->
        products.value = products.value.filter { it != product } // Remove from UI

        coroutineScope.launch(Dispatchers.IO) {
            val retrofitService = RetrofitBuilder.getClient().create(ApiService::class.java)
            retrofitService.deleteCartById(product._id).enqueue(object : Callback<ApiResponse<giohang>> {
                override fun onResponse(call: Call<ApiResponse<giohang>>, response: Response<ApiResponse<giohang>>) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null && responseBody.status == 200) {
                            coroutineScope.launch(Dispatchers.Main) {
                                Toast.makeText(context, "Product deleted successfully", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            coroutineScope.launch(Dispatchers.Main) {
                                Toast.makeText(context, "Failed to delete product: ${responseBody?.messenger}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        coroutineScope.launch(Dispatchers.Main) {
                            Toast.makeText(context, "Failed to delete product: ${response.errorBody()?.string()}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<ApiResponse<giohang>>, t: Throwable) {
                    Log.e("DeleteProductError", "Network failure: ${t.message}", t)
                    coroutineScope.launch(Dispatchers.Main) {
                        Toast.makeText(context, "Network failure: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }


    //tang giảm số luong
    fun updateCartQuantity(productId: String, newQuantity: Int) {

        val retrofitService = RetrofitBuilder.getClient().create(ApiService::class.java)
        retrofitService.updateCartQuantity(productId, newQuantity).enqueue(object :
            Callback<ApiResponse<giohang>> {
            override fun onResponse(call: Call<ApiResponse<giohang>>, response: Response<ApiResponse<giohang>>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null && responseBody.status == 200) {

                        coroutineScope.launch(Dispatchers.Main) {
                            // Cập nhật số lượng trong UI
                            products.value = products.value.map {
                                if (it._id == productId) it.copy(quantity = newQuantity) else it
                            }
                            Toast.makeText(context, "Updated quantity successfully", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(context, "Failed to add distributor: ${responseBody?.messenger}", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "Response not successful: ${response.errorBody()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ApiResponse<giohang>>, t: Throwable) {
                Toast.makeText(context, "No connection", Toast.LENGTH_SHORT).show()
            }
        })
    }




    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "My Cart",
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp)
        )

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(products.value) { item ->
                ProductItem(item = item, onDelete = { deleteProduct(item) },
                    onQuantityChange = { productId, newQuantity ->
                        updateCartQuantity(productId, newQuantity)
                    }
                )
            }
        }



        // Display total price
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Total:",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
            Text(
                text = "$ ${products.value.sumOf { it.price * it.quantity }}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        // Checkout Button
        Button(
            onClick = {  coroutineScope.launch(Dispatchers.IO) {
                val retrofitService = RetrofitBuilder.getClient().create(ApiService::class.java)
                retrofitService.deleteAllCarts().enqueue(object :
                    Callback<ApiResponse<giohang>> {
                    override fun onResponse(call: Call<ApiResponse<giohang>>, response: Response<ApiResponse<giohang>>) {
                        if (response.isSuccessful) {
                            val responseBody = response.body()
                            if (responseBody != null && responseBody.status == 200) {
                                openCheckout()
                                products.value = emptyList() // Clear the cart list // Reset total price

                            } else {
                                // Handle error
                            }
                        } else {
                            // Handle error
                        }
                    }

                    override fun onFailure(call: Call<ApiResponse<giohang>>, t: Throwable) {
                        // Handle failure
                    }
                })
            }
                
    }  ,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Black),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = "Check out",
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun ProductItem(item: giohang, onDelete: (giohang) -> Unit, onQuantityChange: (String, Int) -> Unit) {
    var currentQuantity by remember { mutableStateOf(item.quantity) }
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = item.image,
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .weight(1f)
        ) {
            Text(
                text = item.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "$ ${item.price * currentQuantity}",
                fontSize = 16.sp,
                color = Color.Gray
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(
                    onClick = {
                        if (currentQuantity > 1) {
                            currentQuantity -= 1
                            onQuantityChange(item._id, currentQuantity)
                            Toast.makeText(context, "Decreased quantity", Toast.LENGTH_SHORT).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(Color(0xFF4EC581)),
                    modifier = Modifier.size(30.dp),
                    shape = RoundedCornerShape(5.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(text = "-")
                }
                Text(
                    text = "$currentQuantity",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Button(
                    onClick = {
                        currentQuantity += 1
                        onQuantityChange(item._id, currentQuantity)
                        Toast.makeText(context, "Increased quantity", Toast.LENGTH_SHORT).show()
                    },
                    colors = ButtonDefaults.buttonColors(Color(0xFF4EC581)),
                    modifier = Modifier.size(30.dp),
                    shape = RoundedCornerShape(5.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(text = "+")
                }
            }
        }
        Image(
            painter = painterResource(id = R.drawable.xoa),
            contentDescription = "delete",
            modifier = Modifier
                .size(36.dp)
                .clickable { onDelete(item) }
        )
    }

    LaunchedEffect(currentQuantity) {
        // Empty lambda để đảm bảo mỗi khi currentQuantity thay đổi, UI cũng được cập nhật
    }

}


