package com.example.projectflickr.ui.photoDetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.projectflickr.R
import com.example.projectflickr.models.Photo
import com.example.projectflickr.utils.PicassoUtil
import com.google.gson.Gson
import com.hootsuite.shipmyid.api.ApiErrorResponse
import com.hootsuite.shipmyid.api.ApiSuccessResponse
import kotlinx.android.synthetic.main.flickr_photo_detail.view.*

class FlickrImageDetailFragment : Fragment() {
    private lateinit var rootView : View
    private lateinit var flickrImageDetailViewModel : FlickrImageDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!(::rootView.isInitialized)) {
            rootView = inflater.inflate(R.layout.flickr_photo_detail, container, false)
        }
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        flickrImageDetailViewModel = ViewModelProviders.of(this).get(FlickrImageDetailViewModel::class.java)
        arguments?.let {
            val beanStr = it.getString("photo")
            flickrImageDetailViewModel.photo = Gson().fromJson(beanStr, Photo::class.java)
        }

        val url = "https://farm${flickrImageDetailViewModel.photo?.farm}.staticflickr.com/${flickrImageDetailViewModel.photo?.server}/${flickrImageDetailViewModel.photo?.id}_${flickrImageDetailViewModel.photo?.secret}_b.jpg"

        PicassoUtil.getInstance().executePicasso(context!!, url,rootView.imgFlickrDetail)
        rootView.txtDetailTitle.text = flickrImageDetailViewModel.photo?.title

        flickrImageDetailViewModel.fetchImageList().observe(this, Observer {
            when(it){
                is ApiSuccessResponse -> {
                    val descrption = it.body.photos.description.content
                    val ownerName = it.body.photos.owner.realName
                    rootView.txtOwnerName.text = ownerName
                    rootView.txtPhotoViews.text = it.body.photos.views.toString()
                }

                is ApiErrorResponse -> {
                    Log.d("FAILURE", it.errorMessage)
                }
            }
        })
    }

    companion object {
        fun newInstance (photo: Photo): FlickrImageDetailFragment {
            val bundle = Bundle()
            bundle.putString("photo", Gson().toJson(photo))
            val flickrImageDetail = FlickrImageDetailFragment ()
            flickrImageDetail.arguments = bundle
            return flickrImageDetail
        }
    }
}



