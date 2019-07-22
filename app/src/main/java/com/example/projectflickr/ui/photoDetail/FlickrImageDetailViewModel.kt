package com.example.projectflickr.ui.photoDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.projectflickr.models.Photo
import com.example.projectflickr.models.PhotoDetailResponse
import com.hootsuite.shipmyid.api.ApiResponse
import com.hootsuite.shipmyid.api.FlickrServices
import com.hootsuite.shipmyid.api.ServiceGenerator

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