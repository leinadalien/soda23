package com.example.applicationsimple

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.applicationsimple.data.repositories.BankRepository

class RepositoryViewModelFactory(private val repository: BankRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw java.lang.IllegalArgumentException("unknown viewModel name")
    }
}