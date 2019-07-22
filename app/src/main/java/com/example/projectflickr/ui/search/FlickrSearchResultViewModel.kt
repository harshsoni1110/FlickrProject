package com.example.projectflickr.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.projectflickr.api.Deserializer
import com.example.projectflickr.models.Photo
import com.example.projectflickr.models.PhotoResponse
import com.example.projectflickr.models.Photos
import com.google.gson.reflect.TypeToken
import com.hootsuite.shipmyid.api.*

class FlickrSearchResultViewModel: ViewModel(){

    private val serviceGenerator = ServiceGenerator
    private val fetchImageList: FlickrServices
    val photos = ArrayList<Photo>()
    init {
        fetchImageList = serviceGenerator.createService(FlickrServices::class.java)
    }


    fun fetchImageList (): LiveData<ApiResponse<PhotoResponse>> {
        return fetchImageList.fetchList(tags = "nature")
    }
}