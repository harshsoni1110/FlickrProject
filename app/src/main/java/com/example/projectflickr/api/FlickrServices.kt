package com.hootsuite.shipmyid.api


import androidx.lifecycle.LiveData
import com.example.projectflickr.models.PhotoDetailResponse
import com.example.projectflickr.models.PhotoResponse
import com.example.projectflickr.models.Photos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrServices{

    @GET("rest/")
    fun fetchList(@Query("tags") tags: String, @Query("method") method: String = "flickr.photos.search", @Query("per_page") per_page:Int = 15) : LiveData<ApiResponse<PhotoResponse>>

    @GET("rest/")
    fun fetchImageDetail (@Query("method") method: String = "flickr.photos.getInfo", @Query("photo_id")photoId:String): LiveData<ApiResponse<PhotoDetailResponse>>
}