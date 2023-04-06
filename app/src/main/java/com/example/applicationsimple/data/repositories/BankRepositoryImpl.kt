package com.example.applicationsimple.data.repositories

import com.example.applicationsimple.data.models.BankDepartment
import com.example.applicationsimple.data.models.toBankDepartment
import com.example.applicationsimple.data.remote.RetrofitService
import java.net.SocketTimeoutException

class BankRepositoryImpl : BankRepository {
    override suspend fun getBanksInCity(city: String): List<BankDepartment> {
        var result = listOf<BankDepartment>()
        try {
            val response = RetrofitService.belarusbankAPI.getBanksInCity(city)
            if (response.isSuccessful) {
                response.body()?.let {
                    result = it.map { bankResponse -> bankResponse.toBankDepartment() }
                }
            }
        } catch (ste: SocketTimeoutException) {
            throw ste
        }
        return result
    }
}