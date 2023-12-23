package com.example.newskotlin.Views.News

import com.example.newskotlin.Models.Articles
import com.example.newskotlin.Models.Genres

interface NewsContract {

    interface Presenter {
        fun getData(cat: String)
        fun onDestroy()
    }

    interface View {
        fun initActivity()
        fun onLoading(loading: Boolean)
        fun onResult(dataModel: List<Articles>)
        fun onMessage(message: String)
    }
}