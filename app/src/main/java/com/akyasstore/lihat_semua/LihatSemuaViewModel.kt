package com.akyasstore.lihat_semua

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akyasstore.model.DataBarangModel
import com.akyasstore.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LihatSemuaViewModel(private val repository: Repository):ViewModel() {

    private val _itemsSemuaBarang = MutableLiveData<List<DataBarangModel>>()

    val itemsSemuaBarang : LiveData<List<DataBarangModel>>
        get() = _itemsSemuaBarang

    private val _response = MutableLiveData<String>()

    val response : LiveData<String>
        get() = _response

    private var vmJob = Job()
    private val crScope = CoroutineScope(vmJob + Dispatchers.Main)



    fun kategori(idkategori:String){
        crScope.launch {
            try {
                val barang = repository.KategoriBarang(idkategori)
                if(barang.size > 0) {
                    if (idkategori == "1"){
                        _response.value = "SAYUR"
                    }else if(idkategori == "2") {
                        _response.value = "BUAH"
                    }else if(idkategori == "3") {
                        _response.value = "DAGING"
                    }else if (idkategori == "4"){
                        _response.value = "SEMBAKO"
                    }else{
                        _response.value = "Data Kosong"
                    }
                    _itemsSemuaBarang.value = barang

                }else
                {
                    _response.value = "Data Kosong"
                }



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