package com.example.newskotlin.Models

import com.google.gson.annotations.SerializedName

data class Sources(
    @SerializedName("id") var id: String? = null,
    @SerializedName("name") var name: String? = null,
) {
    fun getSources(): String {
        return "$id $name"
    }
}
