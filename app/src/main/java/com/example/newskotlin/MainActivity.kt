package com.example.newskotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout


class MainActivity : AppCompatActivity() {

    private lateinit var lv_general: LinearLayout
    private lateinit var lv_business: LinearLayout
    private lateinit var lv_entertainment: LinearLayout
    private lateinit var lv_health: LinearLayout
    private lateinit var lv_science: LinearLayout
    private lateinit var lv_sports: LinearLayout
    private lateinit var lv_technology: LinearLayout
    private lateinit var lv_all_categories: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initializing views
        iniView()

        // initializing buttons
        initEvent()

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
}