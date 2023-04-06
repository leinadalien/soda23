package com.example.applicationsimple

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applicationsimple.data.models.BankDepartment
import com.example.applicationsimple.data.repositories.BankRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException

class MainViewModel(private val repository: BankRepository) : ViewModel() {
    private var banksData = MutableLiveData<List<BankDepartment>>()
    fun getBanksInCity(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                banksData.postValue(repository.getBanksInCity(city))
            } catch (ste: SocketTimeoutException) {
                banksData.postValue(emptyList())
            }

        }
    }
    val observableBanks: LiveData<List<BankDepartment>> = banksData
}