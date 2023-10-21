package com.example.newskotlin.Models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Sources(
    @SerializedName("id") var id: String? = null,
    @SerializedName("name") var name: String? = null,
)  : Serializable {
    fun getSources(): String {
        return "$id $name"
    }
}
