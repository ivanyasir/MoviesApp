package com.example.newskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.newskotlin.Models.Articles

class ContentDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_detail)


        val receivedItem = intent.getSerializableExtra("content") as Articles
        Log.d("log", receivedItem.toString())

        val tvTitle = findViewById<TextView>(R.id.tv_title)
        val tvDate = findViewById<TextView>(R.id.tv_date)
        val img1 = findViewById<ImageView>(R.id.img_1)
        val tvDesc = findViewById<TextView>(R.id.tv_description)
        val tvContent = findViewById<TextView>(R.id.tv_content)
        val tvSource = findViewById<TextView>(R.id.tv_source)

        tvTitle.text = receivedItem.title
        tvDate.text = receivedItem.publishedAt
        tvDesc.text = receivedItem.description
        tvContent.text = receivedItem.content
        tvSource.text = receivedItem.url

        Glide.with(this)
            .load(receivedItem.urlToImage)
            .centerCrop()
            .into(img1)
    }
}