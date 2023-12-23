package com.example.newskotlin.Views.News

import android.util.Log
import com.example.newskotlin.Constans
import com.example.newskotlin.Models.ResponseData
import com.example.newskotlin.Services.InterfaceAPI
import com.example.newskotlin.Services.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsPresenter (val view: NewsContract.View): NewsContract.Presenter {

    lateinit var getNewsData: Call<ResponseData>

    init {
        view.initActivity()
    }
    override fun getData(cat: String) {
        val country = "us"
        val category = ""
        val apiKey = Constans.api_key
        val myApi = RetrofitClient.retrofit.create(InterfaceAPI::class.java)
        view.onLoading(true)
        val call: Call<ResponseData> = myApi.getData(country, category, apiKey)
        call.enqueue(
            object : Callback<ResponseData> {
                override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                    Log.d("mainActivity", response.raw().request().url().toString())
                    if (response.isSuccessful) {
                        view.onLoading(false)
                        val data: ResponseData? = response.body()
                        Log.d("mainActivity", "onResponse: $data")

                        if (response.body()?.articles != null) {
                            view.onLoading(false)
                            view.onResult(response.body()!!.articles!!)
                            Log.d("log", response.body()!!.articles!!.size.toString())
                        }
                    } else {
                        view.onLoading(false)
                        Log.d("mainActivity", "onResponse: ${response.code()}")
                        Log.d("mainActivity", "onResponse: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                    view.onLoading(false)
                    Log.d("mainActivity", "onFailure: ${t.message}")

                    if (t.message == "timeout"){
                        view.onMessage("Gagal terhubung pada Server, Mohon Ulangi kembali..")
                    }
                }
            })
    }

    override fun onDestroy() {
        getNewsData.cancel()
    }


}