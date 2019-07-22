package com.example.projectflickr.models

import com.google.gson.annotations.SerializedName

data class PhotoResponse (
    @SerializedName("photos")
    val photos: Photos
)