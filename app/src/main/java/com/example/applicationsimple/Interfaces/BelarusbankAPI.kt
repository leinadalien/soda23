package com.example.applicationsimple.Interfaces

import com.example.applicationsimple.Data.BankDepartment
import retrofit2.http.GET
import retrofit2.http.Query

interface BelarusbankAPI {
    @GET("kursExchange")
    suspend fun getBanksInCity(@Query("city") city: String) : List<BankDepartment>
}