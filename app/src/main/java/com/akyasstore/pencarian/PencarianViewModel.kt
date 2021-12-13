package com.akyasstore.pencarian

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akyasstore.model.DataBarangModel
import com.akyasstore.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PencarianViewModel(private val repository: Repository):ViewModel() {
    private val _itemsPencarian = MutableLiveData<List<DataBarangModel>>()

    val itemsPencarian : LiveData<List<DataBarangModel>>
        get() = _itemsPencarian

    private val _response = MutableLiveData<String>()

    val response : LiveData<String>
        get() = _response

    private var vmJob = Job()
    private val crScope = CoroutineScope(vmJob + Dispatchers.Main)

    fun pencarian(pencarian:String){
        crScope.launch {
            try {
                val pencarian = repository.Pencarian(pencarian)

                if (pencarian.size > 0 ){
                    _itemsPencarian.value = pencarian
                    _response.value = "Data Ada"
                }else{
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