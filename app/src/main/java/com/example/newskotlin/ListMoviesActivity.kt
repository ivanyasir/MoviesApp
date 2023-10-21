package com.example.newskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newskotlin.Adapters.ListMoviesByGenreAdapter
import com.example.newskotlin.Models.*
import com.example.newskotlin.Services.InterfaceAPI
import com.example.newskotlin.Services.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListMoviesActivity : AppCompatActivity(), ListMoviesByGenreAdapter.OnItemClickListener {

    lateinit var adapter: ListMoviesByGenreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_movies)

        val genre = intent.getSerializableExtra("genre") as String
        val genres = intent.getSerializableExtra("genres") as Genres

        Log.d("log", "$genres")

        // hit api movies TMDB
        val loading = findViewById<ProgressBar>(R.id.loading)
        val rvList = findViewById<RecyclerView>(R.id.rv_list_movie_by_genre)
        rvList.layoutManager = LinearLayoutManager(this)

        val myApi = RetrofitClient.retrofit2.create(InterfaceAPI::class.java)
        loading.visibility = View.VISIBLE
        val call: Call<ResMoviesByGenre> = myApi.getMoviesByGenre("Bearer ${Constans.token_bearer}", genre)
        call.enqueue(
            object : Callback<ResMoviesByGenre> {
                override fun onResponse(call: Call<ResMoviesByGenre>, response: Response<ResMoviesByGenre>) {
                    Log.d("log", response.raw().request().url().toString())
                    if (response.isSuccessful) {
                        loading.visibility = View.GONE
                        val data: ResMoviesByGenre? = response.body()
                        Log.d("log", "onResponse: $data")

                        if (response.body()?.results != null) {
                            loading.visibility = View.GONE
                            adapter = ListMoviesByGenreAdapter(this@ListMoviesActivity, response.body()!!.results!!, this@ListMoviesActivity, genres)
                            rvList.adapter = adapter
                            Log.d("log", response.body()!!.results!!.size.toString())
                        }
                    } else {
                        loading.visibility = View.GONE
                        Log.d("log", "onResponse: ${response.code()}")
                        Log.d("log", "onResponse: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<ResMoviesByGenre>, t: Throwable) {
                    loading.visibility = View.GONE
                    Log.d("log", "onFailure: ${t.message}")
                }
            })
    }

    override fun onItemClick(item: Results) {
       Toast.makeText(applicationContext, "Coming Soon... \nNeed more times of development..", Toast.LENGTH_SHORT).show()
    }
}