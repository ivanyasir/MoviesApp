package com.example.newskotlin

import android.hardware.usb.UsbEndpoint
import com.example.newskotlin.Models.ResponseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface InterfaceAPI {

    @GET("top-headlines")
    fun getData(@Query("country") country: String,
                @Query("category") category: String,
                @Query("apiKey") apiKey: String,
    ): Call<ResponseData>
}