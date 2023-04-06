package com.example.applicationsimple.data.remote

import com.example.applicationsimple.data.models.BankResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BelarusbankAPI {
    @GET("kursExchange")
    suspend fun getBanksInCity(@Query("city") city: String) : Response<List<BankResponse>>
}