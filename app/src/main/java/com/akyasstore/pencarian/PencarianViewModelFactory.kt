package com.akyasstore.pencarian

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akyasstore.repository.Repository


class PencarianViewModelFactory (
    private val repository: Repository
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PencarianViewModel(repository) as T
    }
}