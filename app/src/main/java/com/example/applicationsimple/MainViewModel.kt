package com.example.applicationsimple

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applicationsimple.Data.BankDepartment
import com.example.applicationsimple.Data.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private var banksData = MutableLiveData<List<BankDepartment>>()
    fun getBanksInCity(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            banksData.postValue(RetrofitService.belarusbankAPI.getBanksInCity(city))
        }
    }
    val observableBanks: LiveData<List<BankDepartment>> = banksData
}