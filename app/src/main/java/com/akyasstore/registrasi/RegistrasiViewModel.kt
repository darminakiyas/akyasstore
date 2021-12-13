package com.akyasstore.registrasi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akyasstore.model.DataBarangModel
import com.akyasstore.model.RegistrasiModel
import com.akyasstore.model.ResponseModel
import com.akyasstore.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RegistrasiViewModel(private val repository: Repository) : ViewModel() {

    private val _response = MutableLiveData<String>()

    val response : LiveData<String>
        get() = _response


    private val _success = MutableLiveData<Int>()

    val success : LiveData<Int>
        get() = _success

    private var vmJob = Job()
    private val crScope = CoroutineScope(vmJob + Dispatchers.Main)


    fun registrasi(nama_plg:String,email_plg:String,alamat_plg:String,telpon_plg:String,password_plg:String){
        crScope.launch {
            try {
                val success = repository.Registrasi(nama_plg,email_plg,alamat_plg,telpon_plg,password_plg).success
                _success.value = success

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