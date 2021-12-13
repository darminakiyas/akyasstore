package com.akyasstore.repository

import com.akyasstore.model.*
import com.akyasstore.network.Api


class Repository() {
    suspend fun JumlahFavorit():String{
        return Api.retrofitService.JumlahFavorit()
    }
    suspend fun Carousel():List<CarouselModel>{
        return Api.retrofitService.Carousel()
    }
    suspend fun Menu():List<MenuModel>{
        return Api.retrofitService.Menu()
    }
    suspend fun Sayur():List<DataBarangModel>{
        return Api.retrofitService.Sayur()
    }
    suspend fun Buah():List<DataBarangModel>{
        return Api.retrofitService.Buah()
    }
    suspend fun Iklan():List<IklanModel>{
        return Api.retrofitService.Iklan()
    }
    suspend fun Daging():List<DataBarangModel>{
        return Api.retrofitService.Daging()
    }
    suspend fun Sembako():List<DataBarangModel>{
        return Api.retrofitService.Sembako()
    }
    suspend fun KategoriBarang(idKategori:String):List<DataBarangModel>{
        return Api.retrofitService.KategoriBarang(idKategori)
    }
    suspend fun DetailBarang(idBrg:String):DataBarangModel{
        return Api.retrofitService.DetailBarang(idBrg)
    }
    suspend fun likeFavorit(id_user:String,id_brg:String):List<FavoritModel> {
        return Api.retrofitService.likeFavorit(id_user, id_brg)
    }
    suspend fun statusFavorit(id_user:String,id_brg:String):String {
        return Api.retrofitService.statusFavorit(id_user, id_brg)
    }
    suspend fun Pencarian(pencarian:String):List<DataBarangModel> {
        return Api.retrofitService.Pencarian(pencarian)
    }
    suspend fun Registrasi(nama_plg:String,email_plg:String,alamat_plg:String,telpon_plg:String,password_plg:String):ResponseModel {
        return Api.retrofitService.registrasi(nama_plg,email_plg,alamat_plg,telpon_plg,password_plg)
    }
    suspend fun Login(email_plg:String,password_plg:String):ResponseModel {
        return Api.retrofitService.login(email_plg,password_plg)
    }
}