package com.example.cursosapi

import com.google.gson.annotations.SerializedName

data class Curso(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("body")
    val description: String
)