package com.example.applicationsimple.Data

import com.example.applicationsimple.Interfaces.BelarusbankAPI
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitService {
    private val okHttpClient = OkHttpClient.Builder()
        .readTimeout(20, TimeUnit.SECONDS)

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://belarusbank.by/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient.build())
        .build()
    val belarusbankAPI: BelarusbankAPI by lazy {
        retrofit.create(BelarusbankAPI::class.java)
    }

}