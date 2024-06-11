package fpoly.minhpt.kot104_assignment_ph32719

import com.example.ass_ph31495.model.ApiResponse
import com.example.ass_ph31495.model.SanphamKotlin
import com.example.ass_ph31495.model.UsersKotlin
import com.example.ass_ph31495.model.giohang
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @POST("/api/login-kotlin")
    fun login(@Body userKotlin: UsersKotlin) : Call<ApiResponse<UsersKotlin>>

    @POST("/api/signup-kotlin")
    fun signup(@Body userKotlin: UsersKotlin): Call<ApiResponse<UsersKotlin>>

    @GET("/api/getAll-sanpahm")
    fun getListSanpham(): Call<ApiResponse<List<SanphamKotlin>>>

    @GET("/api/get-sanpham-by-id/{id}")
    fun getProductKById(
        @Path("id") productId: String?
    ): Call<ApiResponse<SanphamKotlin>>

    @POST("/api/bill")
    fun bills(@Body bills: giohang): Call<ApiResponse<giohang>>

    @GET("/api/getAll-bill")
    fun getbill(): Call<ApiResponse<List<giohang>>>


    @DELETE("/api/deletebill-kotlin-id/{id}")
    fun deleteCartById(@Path("id") cartId: String?): Call<ApiResponse<giohang>>

    @DELETE("/api/bill-all-carts")
    fun deleteAllCarts(): Call<ApiResponse<giohang>>

    @FormUrlEncoded
    @PUT("/api/update-cart-quantity/{id}")
    fun updateCartQuantity(
        @Path("id") productId: String,
        @Field("quantity") quantity: Int
    ): Call<ApiResponse<giohang>>
}