package com.akyasstore.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akyasstore.repository.Repository


class DetailViewModelFactory (
    private val repository: Repository
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(repository) as T
    }
}