package fpoly.minhpt.kot104_assignment_ph32719


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.ass_ph31495.RetrofitBuilder
import com.example.ass_ph31495.model.ApiResponse
import com.example.ass_ph31495.model.SanphamKotlin
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DanhSachSP : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
        }
    }



@Composable
fun danhsachSP(openCar:()-> Unit,openChitiet: (String)-> Unit) {

    val context = LocalContext.current
    val products = remember { mutableStateOf(listOf<SanphamKotlin>()) }

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch(Dispatchers.IO) {
            val retrofitService = RetrofitBuilder.getClient().create(ApiService::class.java)
            retrofitService.getListSanpham().enqueue(object :
                Callback<ApiResponse<List<SanphamKotlin>>> {
                override fun onResponse(call: Call<ApiResponse<List<SanphamKotlin>>>, response: Response<ApiResponse<List<SanphamKotlin>>>) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null && responseBody.status == 200) {
                            products.value = responseBody.data
                            Toast.makeText(context, "Fetched distributors successfully", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Failed to fetch distributors: ${responseBody?.messenger}", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(context, "Response not successful: ${response.errorBody()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ApiResponse<List<SanphamKotlin>>>, t: Throwable) {
                    Toast.makeText(context, "No connection", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

   Column (modifier = Modifier.fillMaxSize()){
       Row (
           horizontalArrangement = Arrangement.Center,
           modifier = Modifier
               .padding(start = 30.dp),
           ){
           Image(painter = painterResource(id = R.drawable.timkiem), contentDescription ="Timkiem",
               modifier = Modifier
                   .size(90.dp)
                   .padding(end = 50.dp, top = 10.dp))
           Column {
               Text(text = "Make home", fontSize = 20.sp, color = Color(0xff909090), modifier = Modifier.padding(start = 15.dp, top = 20.dp))
               Text(text = "BEAUTIFUL", fontSize = 25.sp, color = Color(0xff000000), fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 10.dp, top = 5.dp))
           }
           Image(painter = painterResource(id = R.drawable.giohang), contentDescription ="giohang",
               modifier = Modifier
                   .size(40.dp)
                   .offset(x = 55.dp, y = 30.dp)
                   .clickable { openCar() })

       }

      Row (
          horizontalArrangement = Arrangement.Center,
      ) {
          Column (horizontalAlignment = Alignment.CenterHorizontally)  {
              Image(painter = painterResource(id = R.drawable.sao2), contentDescription = "sao",
                  modifier = Modifier
                      .width(75.dp)
                      .heightIn(75.dp)
                      .padding(start = 20.dp, top = 0.dp))
              Text(text = "Popular")
          }
          Column (horizontalAlignment = Alignment.CenterHorizontally)  {
              Image(painter = painterResource(id = R.drawable.ghe), contentDescription = "sao",
                  modifier = Modifier
                      .width(75.dp)
                      .heightIn(75.dp)
                      .padding(start = 20.dp, top = 0.dp))
              Text(text = "Chair")
          }
          Column (horizontalAlignment = Alignment.CenterHorizontally) {
              Image(painter = painterResource(id = R.drawable.ban), contentDescription = "sao",
                  modifier = Modifier
                      .width(75.dp)
                      .heightIn(75.dp)
                      .padding(start = 20.dp, top = 0.dp))
              Text(text = "Table")
          }
          Column (horizontalAlignment = Alignment.CenterHorizontally,
              verticalArrangement = Arrangement.Center)  {
              Image(painter = painterResource(id = R.drawable.sopha), contentDescription = "sao",
                  modifier = Modifier
                      .width(75.dp)
                      .heightIn(75.dp)
                      .padding(start = 20.dp, top = 0.dp))
              Text(text = "Armchair")
          }
          Column (horizontalAlignment = Alignment.CenterHorizontally)  {
              Image(painter = painterResource(id = R.drawable.giuong), contentDescription = "sao",
                  modifier = Modifier
                      .width(75.dp)
                      .heightIn(75.dp)
                      .padding(start = 20.dp, top = 0.dp))
              Text(text = "Bed")
          }

      }



       //hien danh sach du lieu
       Column(modifier = Modifier.weight(1f)) {
           ListNormalItem( products.value, openChitiet)
       }


   }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemProduct(click: () -> Unit, image: String, productName: String, price: Int, rate: Double, weight: Double) {
    Card(
        onClick = { click() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .background(color = Color.White)
                .padding(10.dp)
        ) {
            AsyncImage(
                model = image,
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(16.dp)) // Bo góc tròn
            )
            Text(text = productName, fontSize = 20.sp)
            Row {
                Column {
                    Text(text = "Số lượng: $weight", color = Color.Gray, fontSize = 10.sp)
                    Text(text = "$ $price", color = Color(0xFF4EC581))
                }
//                Text(text = "$rate", color = Color(0xFFFF9416))
//                Text(
//                    text = "★",
//                    fontSize = 16.sp,
//                    color = Color(0xFFFF9416),
//                    modifier = Modifier.padding(start = 15.dp)
//                )
            }
        }
    }
}

@Composable
fun ListNormalItem(products: List<SanphamKotlin>, openChitiet: (String) -> Unit) {
    // Replace LazyRow with LazyVerticalGrid and set the column count
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Set to 2 columns, adjust as needed
        contentPadding = PaddingValues(16.dp), // Add padding if needed
        verticalArrangement = Arrangement.spacedBy(16.dp), // Space between items vertically
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        // Space between items horizontally
    ) {
        items(products) { product ->
            ItemProduct(
                click = { openChitiet(product._id) },
                image = product.image,
                productName = product.name,
                price = product.price,
                rate = product.rate,
                weight = product.soluong
            )
        }
    }
}

