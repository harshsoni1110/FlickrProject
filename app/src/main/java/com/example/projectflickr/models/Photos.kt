package com.example.projectflickr.models

import com.google.gson.annotations.SerializedName


data class Photos (

    @SerializedName("page")
    val page: Int,

    @SerializedName("pages")
    val pages:Int,

    @SerializedName("perpage")
    val perPage: Int,

    @SerializedName("total")
    val total: Long,

    @SerializedName("photo")
    val photos: List<Photo>
)