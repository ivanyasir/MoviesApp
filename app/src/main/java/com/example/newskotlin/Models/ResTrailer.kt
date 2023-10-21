package com.example.newskotlin.Models

import com.google.gson.annotations.SerializedName

data class ResTrailer(
    @SerializedName("id") val id: Int? = 0,
    @SerializedName("results") val results: List<ResultsTrailer>? = null,

) : java.io.Serializable
