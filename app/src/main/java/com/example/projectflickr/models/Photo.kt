package com.example.projectflickr.models

import com.google.gson.annotations.SerializedName


data class Photo(
    @SerializedName("id")
    val id: String,

    @SerializedName("owner")
    val owner: String,

    @SerializedName("secret")
    val secret: String,

    @SerializedName("server")
    val server: String,

    @SerializedName("farm")
    val farm: String,

    @SerializedName("title")
    val title: String

    )