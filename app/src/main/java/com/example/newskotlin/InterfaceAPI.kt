package com.example.newskotlin

import com.example.newskotlin.Models.ResponseData
import retrofit2.Call
import retrofit2.http.GET

interface InterfaceAPI {

    @GET("everything?q=tesla&from=2023-09-20&sortBy=publishedAt&apiKey=1c3228574a4c48d39c1940a26da79443")
    fun getData(): Call<ResponseData>
}