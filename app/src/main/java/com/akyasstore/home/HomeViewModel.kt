package com.akyasstore.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akyasstore.model.CarouselModel
import com.akyasstore.model.DataBarangModel
import com.akyasstore.model.IklanModel
import com.akyasstore.model.MenuModel
import com.akyasstore.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch



class HomeViewModel(private val repository:Repository):ViewModel() {
    private val _jumlahFavorit = MutableLiveData<String>()

    val jumlahFavorit : LiveData<String>
        get() = _jumlahFavorit

    private val _carousel = MutableLiveData<List<CarouselModel>>()

    val carousel : LiveData<List<CarouselModel>>
        get() = _carousel

    private val _menu = MutableLiveData<List<MenuModel>>()

    val menu : LiveData<List<MenuModel>>
        get() = _menu

    private val _sayur = MutableLiveData<List<DataBarangModel>>()

    val sayur : LiveData<List<DataBarangModel>>
        get() = _sayur

    private val _buah = MutableLiveData<List<DataBarangModel>>()

    val buah : LiveData<List<DataBarangModel>>
        get() = _buah

    private val _iklan = MutableLiveData<List<IklanModel>>()

    val iklan : LiveData<List<IklanModel>>
        get() = _iklan

    private val _daging = MutableLiveData<List<DataBarangModel>>()

    val daging : LiveData<List<DataBarangModel>>
        get() = _daging

    private val _sembako = MutableLiveData<List<DataBarangModel>>()

    val sembako : LiveData<List<DataBarangModel>>
        get() = _sembako

    private val _response = MutableLiveData<String>()

    val response : LiveData<String>
        get() = _response

    private var vmJob = Job()
    private val crScope = CoroutineScope(vmJob + Dispatchers.Main)


    init {
        _response.value ="Loading ..."
        crScope.launch {
            try {
                val jumlah = repository.JumlahFavorit()
                val carousel = repository.Carousel()
                val menu = repository.Menu()
                val sayur = repository.Sayur()
                val buah = repository.Buah()
                val iklan = repository.Iklan()
                val daging = repository.Daging()
                val sembako = repository.Sembako()

                if(menu.size > 0) {
                _jumlahFavorit.value = jumlah
                _carousel.value = carousel
                _menu.value = menu
                _sayur.value = sayur
                _buah.value = buah
                _iklan.value = iklan
                _daging.value = daging
                _sembako.value = sembako
                    _response.value = "OK"
                }


            } catch (t: Throwable) {
                _response.value = "Failed, Internet OFF"
                //show error

            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        vmJob.cancel()
    }


}