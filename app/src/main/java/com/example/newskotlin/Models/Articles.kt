package com.example.newskotlin.Models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Articles(
    @SerializedName("source") var source: Sources? = null,
    @SerializedName("author") var author: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("urlToImage") var urlToImage: String? = null,
    @SerializedName("publishedAt") var publishedAt: String? = null,
    @SerializedName("content") var content: String? = null,

    ) : Serializable {
    fun getArticles(): String {
        return "$source $author $title $description $url $urlToImage $publishedAt $content"
    }
}
