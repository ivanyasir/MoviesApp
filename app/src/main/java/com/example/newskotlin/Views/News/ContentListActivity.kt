package com.example.newskotlin.Views.News

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newskotlin.Adapters.ListTitleAdapter
import com.example.newskotlin.Models.Articles
import com.example.newskotlin.R

class ContentListActivity : AppCompatActivity(), NewsContract.View, ListTitleAdapter.OnItemClickListener {

    lateinit var adapter: ListTitleAdapter
    lateinit var loading: ProgressBar
    lateinit var rv1: RecyclerView
    private lateinit var newsPresenter: NewsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)




        // preset data
        var category = ""
        val categories = intent.getStringExtra("categories")
        if (categories != null) {
            category = categories
        }

        // request data from API <newsAPI>
        newsPresenter = NewsPresenter(this)
        newsPresenter.getData(category)
    }

    override fun onItemClick(item: Articles) {
        val intent = Intent(this@ContentListActivity, ContentDetailActivity::class.java)
        intent.putExtra("content", item)
        startActivity(intent)
    }

    override fun initActivity() {

        loading = findViewById<ProgressBar>(R.id.loading)
        rv1 = findViewById<RecyclerView>(R.id.rv_1)

    }

    override fun onLoading(loadingState: Boolean) {
       if (loadingState){
           loading.visibility = View.VISIBLE
       } else {
           loading.visibility = View.GONE
       }
    }

    override fun onResult(dataModel: List<Articles>) {
        rv1.layoutManager = LinearLayoutManager(this)
        adapter = ListTitleAdapter(dataModel, this@ContentListActivity)
        rv1.adapter = adapter
    }

    override fun onMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}