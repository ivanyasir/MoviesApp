package com.example.newskotlin.Views.Movies

import android.util.Log
import com.example.newskotlin.Constans
import com.example.newskotlin.Models.ResponseMovies
import com.example.newskotlin.Services.InterfaceAPI
import com.example.newskotlin.Services.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviePresenter(val view: MoviesContract.View) : MoviesContract.Presenter {

    lateinit var getMoviesData: Call<ResponseMovies>
    init {
        view.initActivity()
    }

    override fun getData() {
        val myApi = RetrofitClient.retrofit2.create(InterfaceAPI::class.java)
        view.onLoading(true)
        val call: Call<ResponseMovies> = myApi.getMovies("Bearer ${Constans.token_bearer}", "en")
        call.enqueue(
            object : Callback<ResponseMovies> {
                override fun onResponse(call: Call<ResponseMovies>, response: Response<ResponseMovies>) {
                    Log.d("mainActivity", response.raw().request().url().toString())
                    if (response.isSuccessful) {
                        view.onLoading(false)
                        val data: ResponseMovies? = response.body()
                        Log.d("log", "onResponse: $data")

                        if (response.body()?.genres != null) {
                            view.onLoading(false)
                            view.onResult(response.body()!!.genres!!)

                            Log.d("log", response.body()!!.genres!!.size.toString())
                        }
                    } else {
                        view.onLoading(false)
                        Log.d("log", "onResponse: ${response.code()}")
                        Log.d("log", "onResponse: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<ResponseMovies>, t: Throwable) {
                    view.onLoading(false)
                    Log.d("log", "onFailure: ${t.message}")
                }
            })
    }

    override fun onDestroy() {
        getMoviesData.cancel()
    }
}