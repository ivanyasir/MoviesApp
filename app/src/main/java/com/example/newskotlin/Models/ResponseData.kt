package com.example.newskotlin.Models

import com.google.gson.annotations.SerializedName

data class ResponseData(
    @SerializedName("status" ) var status : String? = null,
    @SerializedName("totalResults" ) var totalResults : Int? = null,
    @SerializedName("articles" ) var articles : List<Articles>? = null,

) {
    fun getResponseData(): String {
        return "$status $totalResults $articles"
    }

}