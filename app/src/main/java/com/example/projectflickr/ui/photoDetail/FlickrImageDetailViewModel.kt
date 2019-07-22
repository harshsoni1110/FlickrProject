package com.example.projectflickr.ui.photoDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.projectflickr.api.Deserializer
import com.example.projectflickr.models.Photo
import com.example.projectflickr.models.PhotoDetailResponse
import com.example.projectflickr.models.PhotoResponse
import com.example.projectflickr.models.Photos
import com.google.gson.reflect.TypeToken
import com.hootsuite.shipmyid.api.*

class FlickrImageDetailViewModel: ViewModel(){

    var photo: Photo? = null
    private val serviceGenerator = ServiceGenerator
    private val fetchImageList: FlickrServices
    val photos = ArrayList<Photo>()
    init {
        fetchImageList = serviceGenerator.createService(FlickrServices::class.java)
    }


    fun fetchImageList (id: String): LiveData<ApiResponse<PhotoDetailResponse>> {
        return fetchImageList.fetchImageDetail(photoId = id)

    }
}