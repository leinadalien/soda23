package com.example.applicationsimple.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitService {
    private val okHttpClient = OkHttpClient.Builder()
        .readTimeout(2, TimeUnit.MINUTES)

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://belarusbank.by/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient.build())
        .build()
    val belarusbankAPI: BelarusbankAPI by lazy {
        retrofit.create(BelarusbankAPI::class.java)
    }

}