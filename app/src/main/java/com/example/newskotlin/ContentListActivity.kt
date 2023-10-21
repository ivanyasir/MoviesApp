package com.example.newskotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newskotlin.Adapters.ListTitleAdapter
import com.example.newskotlin.Models.Articles
import com.example.newskotlin.Models.ResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContentListActivity : AppCompatActivity(), ListTitleAdapter.OnItemClickListener {

    lateinit var adapter: ListTitleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)

        val loading = findViewById<ProgressBar>(R.id.loading)
        val rv1 = findViewById<RecyclerView>(R.id.rv_1)
        rv1.layoutManager = LinearLayoutManager(this)


        // preset data
        var country = "us"
        var category = ""
//        var pageSize = "\""
        val apiKey = Constans.api_key

        val categories = intent.getStringExtra("categories")
        if (categories != null) {
            category = categories
        }

        // request data from API <newsAPI>
        val myApi = RetrofitClient.retrofit.create(InterfaceAPI::class.java)
        loading.visibility = View.VISIBLE
        val call: Call<ResponseData> = myApi.getData(country, category, apiKey)
        call.enqueue(
            object : Callback<ResponseData> {
                override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                    Log.d("mainActivity", response.raw().request().url().toString())
                    if (response.isSuccessful) {
                        loading.visibility = View.GONE
                        val data: ResponseData? = response.body()
                        Log.d("mainActivity", "onResponse: $data")

                        if (response.body()?.articles != null) {
                            loading.visibility = View.GONE
                            adapter = ListTitleAdapter(response.body()!!.articles!!, this@ContentListActivity)
                            rv1.adapter = adapter
                            Log.d("log", response.body()!!.articles!!.size.toString())
                        }
                    } else {
                        loading.visibility = View.GONE
                        Log.d("mainActivity", "onResponse: ${response.code()}")
                        Log.d("mainActivity", "onResponse: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                    loading.visibility = View.GONE
                    Log.d("mainActivity", "onFailure: ${t.message}")
                }
            })
    }

    override fun onItemClick(item: Articles) {
        val intent = Intent(this@ContentListActivity, ContentDetailActivity::class.java)
        intent.putExtra("content", item)
        startActivity(intent)
    }


}