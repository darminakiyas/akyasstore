package com.akyasstore.network


import com.akyasstore.model.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*


val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(AlamatUrl.BASE_URL)
    .build()

interface ApiService {
    @GET("api/api/numFavorit/2")
    suspend fun JumlahFavorit(): String

    @GET("api/api/carousel")
    suspend fun Carousel():
            List<CarouselModel>

    @GET("api/api/kategori")
    suspend fun Menu():
            List<MenuModel>

    @GET("api/api/getSayur")
    suspend fun Sayur():
            List<DataBarangModel>

    @GET("api/api/getBuah")
    suspend fun Buah():
            List<DataBarangModel>

    @GET("api/api/getIklan")
    suspend fun Iklan():
            List<IklanModel>

    @GET("api/api/getDaging")
    suspend fun Daging():
            List<DataBarangModel>

    @GET("api/api/getSembako")
    suspend fun Sembako():
            List<DataBarangModel>

    @GET("api/api/getNamaSayur/{idKategori}")
    suspend fun KategoriBarang(@Path("idKategori") idkategori: String):
            List<DataBarangModel>

    @GET("api/api/getnamabarang/{idbrg}")
    suspend fun DetailBarang(@Path("idbrg") idBrg: String):
            DataBarangModel

    @FormUrlEncoded
    @POST("api/api/pencarian/{pencarian}")
    suspend fun Pencarian(@Field("pencarian")pencarian: String):
            List<DataBarangModel>

    @FormUrlEncoded
    @POST("api/api/tambahFavorit/")
    suspend fun likeFavorit(
        @Field("id_user") id_user: String,
        @Field("id_brg") id_brg: String
    ):
            List<FavoritModel>

    @FormUrlEncoded
    @POST("api/api/statusFavorit/")
    suspend fun statusFavorit(
        @Field("id_user") id_user: String,
        @Field("id_brg") id_brg: String
    ):
            String

    @FormUrlEncoded
    @POST("api/api/registrasi/")
    suspend fun registrasi(
        @Field("nama_plg") nama_plg: String,
        @Field("email_plg") email_plg: String,
        @Field("alamat_plg") alamat_plg: String,
        @Field("telpon_plg") telpon_plg: String,
        @Field("password_plg") password_plg: String,

    ):
            ResponseModel

    @FormUrlEncoded
    @POST("api/api/login/")
    suspend fun login(
        @Field("email_plg") email_plg: String,
        @Field("password_plg") password_plg: String,
        ):
            ResponseModel
}

object Api{
    val retrofitService = retrofit.create(ApiService::class.java)
}