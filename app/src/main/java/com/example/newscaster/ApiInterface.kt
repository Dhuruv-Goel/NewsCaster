package com.example.newscaster

import com.example.newscaster.DataClass.mainNewsClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("top-headlines")
    fun getNews(
        @Query("country") country:String?=null,
        @Query("pageSize") pageSize:Int?=null,
        @Query("apiKey") apiKey:String?=null
    ): Call<mainNewsClass?>

    @GET("top-headlines")
    fun getCategoryNews(
        @Query("country") country:String?=null,
        @Query("category") category:String?=null,
        @Query("pageSize") pageSize:Int?=null,
        @Query("apiKey") apiKey:String?=null
    ): Call<mainNewsClass?>


}