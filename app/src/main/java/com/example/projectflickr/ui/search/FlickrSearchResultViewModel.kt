package com.example.projectflickr.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.projectflickr.models.Photo
import com.example.projectflickr.models.PhotoResponse
import com.hootsuite.shipmyid.api.ApiResponse
import com.hootsuite.shipmyid.api.FlickrServices
import com.hootsuite.shipmyid.api.ServiceGenerator

class FlickrSearchResultViewModel: ViewModel(){

    private val serviceGenerator = ServiceGenerator
    private val fetchImageList: FlickrServices
    val photos = ArrayList<Photo>()
    init {
        fetchImageList = serviceGenerator.createService(FlickrServices::class.java)
    }


    fun fetchImageList (searchKey: String): LiveData<ApiResponse<PhotoResponse>> {
        return fetchImageList.fetchList(tags = searchKey)
    }
}