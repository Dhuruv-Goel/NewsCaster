package com.example.newscaster

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//const val BASE_URL="https://newsapi.org/v2/"
object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }
}
