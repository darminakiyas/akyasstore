package com.akyasstore.lihat_semua

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akyasstore.repository.Repository


class LihatSemuaViewModelFactory (
    private val repository: Repository
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LihatSemuaViewModel(repository) as T
    }
}