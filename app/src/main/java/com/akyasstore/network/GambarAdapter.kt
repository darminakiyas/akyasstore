package com.akyasstore.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.akyasstore.R
import com.akyasstore.network.AlamatUrl
import com.bumptech.glide.Glide


@BindingAdapter("showImage")
fun showImage(imgView:ImageView,gambar:String?){
    Glide.with(imgView.context)
        .load(AlamatUrl.BASE_URL+"assets/gambar/"+gambar)
        //.placeholder(R.drawable.placeholder)
        //.fitCenter()
        .into(imgView);
}

@BindingAdapter("showIcon")
fun showIcon(imgView:ImageView,icon:String?){
    Glide.with(imgView.context)
        .load(AlamatUrl.BASE_URL+"assets/icon/"+icon)
        //.placeholder(R.drawable.placeholder)
        //.fitCenter()
        .into(imgView);
}
@BindingAdapter("showIklan")
fun showIklan(imgView:ImageView,gambar:String?){
    Glide.with(imgView.context)
        .load(AlamatUrl.BASE_URL+"assets/iklan/"+gambar)
        //.placeholder(R.drawable.placeholder)
        //.fitCenter()
        .into(imgView);
}
@BindingAdapter("LoadStatus")
fun loadStatus(textView: TextView, status:String?){
    if(status == "OK"){
        textView.visibility = View.GONE
    }
}


@BindingAdapter("loadStatusGambar")
fun loadStatusGambar(img: ImageView, status:String?){
    if(status == "BIRU"){
        img.visibility = View.GONE
    }
}
