package com.akyasstore.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akyasstore.model.DataBarangModel
import com.akyasstore.model.FavoritModel
import com.akyasstore.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailViewModel(private val repository:Repository):ViewModel() {
    private val _detailBarang = MutableLiveData<DataBarangModel>()

    val detailBarang : LiveData<DataBarangModel>
        get() = _detailBarang

    private val _itemsRekomendasi = MutableLiveData<List<DataBarangModel>>()

    val itemsRekomendasi : LiveData<List<DataBarangModel>>
        get() = _itemsRekomendasi

    private val _gambarFavorit = MutableLiveData<String>()

    val gambarFavorit : LiveData<String>
        get() = _gambarFavorit

    private val _likeFavorit = MutableLiveData<List<FavoritModel>>()

    val likeFavorit : LiveData<List<FavoritModel>>
        get() = _likeFavorit

    private val _statusFavorit = MutableLiveData<String>()

    val statusFavorit : LiveData<String>
        get() = _statusFavorit

    private var vmJob = Job()
    private val crScope = CoroutineScope(vmJob + Dispatchers.Main)

    init {
    _gambarFavorit.value = "MERAH"
    }

    fun detailBarang(idBrg:String){
        crScope.launch {
            try {
                val detailBarang = repository.DetailBarang(idBrg)
                val idKategori = repository.DetailBarang(idBrg).id_kategori
                val rekomendasi = repository.KategoriBarang(idKategori)

                _detailBarang.value = detailBarang
                _itemsRekomendasi.value = rekomendasi

            } catch (t: Throwable) {
                //show error
            }
        }
    }

    fun likeFavorit(idUser:String,idBrg:String){
        crScope.launch {
            try {
                val likeFavorit = repository.likeFavorit(idUser,idBrg)
                val statuFavorit = repository.statusFavorit(idUser,idBrg)
                Log.d("coba ","baru "+statuFavorit)
                _statusFavorit.value = statuFavorit
                _likeFavorit.value = likeFavorit
            } catch (t: Throwable) {
                //show error
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        vmJob.cancel()
    }
}