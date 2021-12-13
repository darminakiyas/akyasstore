package com.akyasstore.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akyasstore.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoginViewModel(private val repository:Repository):ViewModel() {

    private val _response = MutableLiveData<String>()

    val response : LiveData<String>
        get() = _response


    private val _success = MutableLiveData<Int>()

    val success : LiveData<Int>
        get() = _success

    private var vmJob = Job()
    private val crScope = CoroutineScope(vmJob + Dispatchers.Main)


    fun login(email_plg:String,password_plg:String){
        crScope.launch {
            try {
                val success = repository.Login(email_plg,password_plg).success
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