package com.example.newskotlin.Models

import com.google.gson.annotations.SerializedName

data class ResMoviesByGenre(
    @SerializedName("page") val page: Int? = 0,
    @SerializedName("total_pages") val total_pages: Int? = 0,
    @SerializedName("total_results") val total_results: Int? = 0,
    @SerializedName("results") val results: List<Results>? = null
) : java.io.Serializable {
    fun getResMovieByGenre(): String {
        return "$page $results"
    }
}
