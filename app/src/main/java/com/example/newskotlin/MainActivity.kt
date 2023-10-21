package com.example.newskotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newskotlin.Adapters.GenresAdapter
import com.example.newskotlin.Models.Genres
import com.example.newskotlin.Models.ResponseMovies
import com.example.newskotlin.Services.InterfaceAPI
import com.example.newskotlin.Services.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), GenresAdapter.OnItemClickListener {

    private lateinit var lv_general: LinearLayout
    private lateinit var lv_business: LinearLayout
    private lateinit var lv_entertainment: LinearLayout
    private lateinit var lv_health: LinearLayout
    private lateinit var lv_science: LinearLayout
    private lateinit var lv_sports: LinearLayout
    private lateinit var lv_technology: LinearLayout
    private lateinit var lv_all_categories: LinearLayout
    private lateinit var loading: ProgressBar
    private lateinit var rvGenres: RecyclerView
    lateinit var adapter: GenresAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initializing views
        iniView()

        // initializing buttons
        initEvent()

        rvGenres.layoutManager = LinearLayoutManager(this)

        val myApi = RetrofitClient.retrofit2.create(InterfaceAPI::class.java)
        loading.visibility = View.VISIBLE
        val call: Call<ResponseMovies> = myApi.getMovies("Bearer ${Constans.token_bearer}", "en")
        call.enqueue(
            object : Callback<ResponseMovies> {
                override fun onResponse(call: Call<ResponseMovies>, response: Response<ResponseMovies>) {
                    Log.d("mainActivity", response.raw().request().url().toString())
                    if (response.isSuccessful) {
                        loading.visibility = View.GONE
                        val data: ResponseMovies? = response.body()
                        Log.d("log", "onResponse: $data")

                        if (response.body()?.genres != null) {
                            loading.visibility = View.GONE
                            adapter = GenresAdapter(response.body()!!.genres!!, this@MainActivity)
                            rvGenres.adapter = adapter
                            Log.d("log", response.body()!!.genres!!.size.toString())
                        }
                    } else {
                        loading.visibility = View.GONE
                        Log.d("log", "onResponse: ${response.code()}")
                        Log.d("log", "onResponse: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<ResponseMovies>, t: Throwable) {
                    loading.visibility = View.GONE
                    Log.d("log", "onFailure: ${t.message}")
                }
            })
    }

    private fun iniView() {
        lv_general = findViewById<LinearLayout>(R.id.lv_general)
        lv_business = findViewById<LinearLayout>(R.id.lv_busisness)
        lv_entertainment = findViewById<LinearLayout>(R.id.lv_entertainment)
        lv_health = findViewById<LinearLayout>(R.id.lv_health)
        lv_science = findViewById<LinearLayout>(R.id.lv_science)
        lv_sports = findViewById<LinearLayout>(R.id.lv_sports)
        lv_technology = findViewById<LinearLayout>(R.id.lv_technology)
        lv_all_categories = findViewById<LinearLayout>(R.id.lv_all_categories)
        loading = findViewById<ProgressBar>(R.id.loading)
        rvGenres = findViewById<RecyclerView>(R.id.rv_genres)
    }

    private fun initEvent() {
        lv_general.setOnClickListener {
            val intent = Intent(this, ContentListActivity::class.java)
            intent.putExtra("categories", "general")
            startActivity(intent)
        }
        lv_business.setOnClickListener {
            val intent = Intent(this, ContentListActivity::class.java)
            intent.putExtra("categories", "business")
            startActivity(intent)
        }
        lv_entertainment.setOnClickListener {
            val intent = Intent(this, ContentListActivity::class.java)
            intent.putExtra("categories", "entertainment")
            startActivity(intent)
        }
        lv_health.setOnClickListener {
            val intent = Intent(this, ContentListActivity::class.java)
            intent.putExtra("categories", "health")
            startActivity(intent)
        }
        lv_science.setOnClickListener {
            val intent = Intent(this, ContentListActivity::class.java)
            intent.putExtra("categories", "science")
            startActivity(intent)
        }
        lv_sports.setOnClickListener {
            val intent = Intent(this, ContentListActivity::class.java)
            intent.putExtra("categories", "sports")
            startActivity(intent)
        }
        lv_technology.setOnClickListener {
            val intent = Intent(this, ContentListActivity::class.java)
            intent.putExtra("categories", "technology")
            startActivity(intent)
        }
        lv_all_categories.setOnClickListener {
            val intent = Intent(this, ContentListActivity::class.java)
            intent.putExtra("categories", "")
            startActivity(intent)
        }
    }

    override fun onItemClick(item: Genres) {
        val i = Intent(this, ListMoviesActivity::class.java)
        i.putExtra("genre", item.name)
        i.putExtra("genres", item)
        startActivity(i)
    }
}