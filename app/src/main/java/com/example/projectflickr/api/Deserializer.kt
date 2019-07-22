package com.example.projectflickr.api

import android.util.Log
import com.example.projectflickr.models.Photos
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class Deserializer  : JsonDeserializer<List<Photos>> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): List<Photos> {
        val item = ArrayList <Photos>()
        Log.d("RESPONSE", json?.asJsonObject.toString())
        return item
    }
}