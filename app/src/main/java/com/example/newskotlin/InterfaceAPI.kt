package com.example.newskotlin

import com.example.newskotlin.Models.ResponseData
import retrofit2.Call
import retrofit2.http.GET

interface InterfaceAPI {

    @GET("endpoint/path")
    fun getData(): Call<ResponseData>
}