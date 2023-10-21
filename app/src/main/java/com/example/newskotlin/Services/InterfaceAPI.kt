package com.example.newskotlin.Services

import android.hardware.usb.UsbEndpoint
import com.example.newskotlin.Models.ResMoviesByGenre
import com.example.newskotlin.Models.ResTrailer
import com.example.newskotlin.Models.ResponseData
import com.example.newskotlin.Models.ResponseMovies
import org.intellij.lang.annotations.Language
import retrofit2.Call
import retrofit2.http.*

interface InterfaceAPI {

    @GET("top-headlines")
    fun getData(@Query("country") country: String,
                @Query("category") category: String,
                @Query("apiKey") apiKey: String,
    ): Call<ResponseData>

    @GET("genre/movie/list")
    fun getMovies(@Header("Authorization") token: String, @Query("language") language: String ): Call<ResponseMovies>

    @GET("discover/movie")
    fun getMoviesByGenre(@Header("Authorization") token: String, @Query("with_genres") genre: String) : Call<ResMoviesByGenre>

    @GET("movie/{key}/videos")
    fun getTrailer(@Header("Authorization") token: String, @Path("key") key:String): Call<ResTrailer>
}