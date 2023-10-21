package com.example.newskotlin.Models

import com.google.gson.annotations.SerializedName

data class ResponseMovies(
    @SerializedName("genres") var genres: List<Genres>? = null
) : java.io.Serializable {
    fun getMoviesData() : String {
        return "$genres"
    }
}
