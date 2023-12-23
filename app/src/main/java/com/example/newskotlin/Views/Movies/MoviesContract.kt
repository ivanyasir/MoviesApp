package com.example.newskotlin.Views.Movies

import com.example.newskotlin.Models.Genres
import com.example.newskotlin.Models.ResponseMovies

interface MoviesContract {

    interface Presenter {
        fun getData()
        fun onDestroy()
    }

    interface View {
        fun initActivity()
        fun onLoading(loading: Boolean)
        fun onResult(dataModel: List<Genres>)
        fun onMessage(message: String)
    }
}