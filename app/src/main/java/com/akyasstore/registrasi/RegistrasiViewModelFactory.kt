package com.akyasstore.registrasi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akyasstore.repository.Repository


class RegistrasiViewModelFactory (
    private val repository: Repository
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegistrasiViewModel(repository) as T
    }
}