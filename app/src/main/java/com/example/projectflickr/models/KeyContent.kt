package com.example.projectflickr.models

import com.google.gson.annotations.SerializedName

data class KeyContent (

    @SerializedName("_content")
    val content: String
)