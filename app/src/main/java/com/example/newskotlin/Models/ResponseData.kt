package com.example.newskotlin.Models

data class ResponseData(
    val status: String,
    val lastName: String,
    val age: Int
) {
    fun getData(): String {
        return "$status $lastName $age"
    }

}