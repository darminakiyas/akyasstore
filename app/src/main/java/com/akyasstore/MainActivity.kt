package com.akyasstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    //private var backPressedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




    }
    //untuk pesan ketika ingin keluar dari aplikasi
//    override fun onBackPressed(){
//        if(backPressedTime + 2000 > System.currentTimeMillis()){
//            super.onBackPressed()
//        }else{
//
//            Toast.makeText(this,"Tekan Lagi Untuk Keluar",Toast.LENGTH_SHORT).show()
//        }
//        backPressedTime = System.currentTimeMillis()
//    }


}