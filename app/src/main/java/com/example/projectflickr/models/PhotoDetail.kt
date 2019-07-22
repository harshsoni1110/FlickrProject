package com.example.projectflickr.models

import com.google.gson.annotations.SerializedName

data class PhotoDetail (
    @SerializedName("id")
    val id: String,

    @SerializedName("secret")
    val secret: String,

    @SerializedName("server")
    val server: String,

    @SerializedName("farm")
    val farm: String,

    @SerializedName("owner")
    val owner: Person,

    @SerializedName("title")
    val title: KeyContent,


    @SerializedName("description")
    val description: KeyContent,

    @SerializedName("views")
    val views: Long

)