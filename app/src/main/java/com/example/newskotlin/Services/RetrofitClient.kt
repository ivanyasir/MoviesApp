package com.example.newskotlin.Services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://newsapi.org/v2/"
    private const val BASE_URL2 = "https://api.themoviedb.org/3/"

    // News API
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // TMDB Movies API
    val retrofit2: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL2)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}