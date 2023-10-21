package com.example.newskotlin.Models

import com.google.gson.annotations.SerializedName

data class ResultsTrailer(
    @SerializedName("key") val key : String? = ""
) : java.io.Serializable
