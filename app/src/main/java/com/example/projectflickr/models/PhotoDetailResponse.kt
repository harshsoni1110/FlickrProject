package com.example.projectflickr.models

import com.google.gson.annotations.SerializedName

data class PhotoDetailResponse (
    @SerializedName("photo")
    val photos: Photo
)