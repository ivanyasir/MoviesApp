package com.example.newskotlin.Views.Movies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newskotlin.Adapters.GenresAdapter
import com.example.newskotlin.Views.News.ContentListActivity
import com.example.newskotlin.Models.Genres
import com.example.newskotlin.R


class MainActivity : AppCompatActivity(), MoviesContract.View, GenresAdapter.OnItemClickListener {

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
    private lateinit var moviesPresenter: MoviePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initializing views
        iniView()

        // initializing buttons
        initEvent()

        moviesPresenter = MoviePresenter(this)
        moviesPresenter.getData()


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

    override fun initActivity() {

        loading = findViewById<ProgressBar>(R.id.loading)
        rvGenres = findViewById<RecyclerView>(R.id.rv_genres)
    }

    override fun onLoading(loadingState: Boolean) {
        if (loadingState){
            loading.visibility = View.VISIBLE
        } else {
            loading.visibility = View.GONE
        }
    }

    override fun onResult(dataModel: List<Genres>) {
        rvGenres.layoutManager = LinearLayoutManager(this)
        adapter = GenresAdapter(dataModel, this@MainActivity)
        rvGenres.adapter = adapter
    }

    override fun onMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}