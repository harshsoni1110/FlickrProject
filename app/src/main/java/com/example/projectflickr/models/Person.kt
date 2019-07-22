package com.example.projectflickr.models

import com.google.gson.annotations.SerializedName

data class Person (
    @SerializedName("nsid")
    val nsid: String,

    @SerializedName("username")
    val userName: String,

    @SerializedName("realname")
    val realName: String,

    @SerializedName("location")
    val location: String
)