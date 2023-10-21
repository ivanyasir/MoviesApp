package com.example.newskotlin.Models

import com.google.gson.annotations.SerializedName

data class Genres(
    @SerializedName("id") val id: Int? = 0,
    @SerializedName("name") val name: String? = ""
) : java.io.Serializable {
    fun getGenres(): String {
        return "$id $name"
    }
}
