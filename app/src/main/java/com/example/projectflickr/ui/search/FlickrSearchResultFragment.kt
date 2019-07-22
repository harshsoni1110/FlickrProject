package com.example.projectflickr.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectflickr.R
import com.example.projectflickr.models.Photo
import com.example.projectflickr.ui.photoDetail.FlickrImageDetail
import com.google.android.material.snackbar.Snackbar
import com.hootsuite.shipmyid.api.ApiErrorResponse
import com.hootsuite.shipmyid.api.ApiSuccessResponse
import kotlinx.android.synthetic.main.list_of_phots.view.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FlickrSearchResultFragment : Fragment(), FlickImageListAdapter.FlickrImageTapListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var rootView : View
    private lateinit var flickrSearchResultViewModel : FlickrSearchResultViewModel
    private lateinit var flickImageListAdapter: FlickImageListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!(::rootView.isInitialized)) {
            rootView = inflater.inflate(R.layout.list_of_phots, container, false)
        }
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        flickrSearchResultViewModel = ViewModelProviders.of(this).get(FlickrSearchResultViewModel::class.java)
        flickImageListAdapter = FlickImageListAdapter(flickrSearchResultViewModel.photos, this)

        rootView.rcyList.apply {
            adapter = flickImageListAdapter
            layoutManager = LinearLayoutManager(context)
        }
        var searchKey = ""
        arguments?.let {
            searchKey = it.getString("searchKey") ?: ""
        }

        flickrSearchResultViewModel.fetchImageList(searchKey).observe(this, Observer {
            when(it){
                is ApiSuccessResponse -> {
                    rootView.progressBarFlickrPhotos.visibility = View.GONE
                    rootView.rcyList.visibility = View.VISIBLE
                    flickrSearchResultViewModel.photos.addAll(it.body.photos.photos)
                    flickImageListAdapter.notifyDataSetChanged()

                }

                is ApiErrorResponse -> {
                    rootView.progressBarFlickrPhotos.visibility = View.GONE
                    rootView.rcyList.visibility = View.GONE
                    Snackbar.make(rootView , resources.getString(R.string.something_went_wrong), Snackbar.LENGTH_LONG).show()
                    Log.d("FAILURE", it.errorMessage)
                }
            }
        })
    }

    override fun onImageClick(photo: Photo) {
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.lytContainer, FlickrImageDetail.newInstance(photo))?.addToBackStack(null)?.commit()
    }

    companion object {
        fun newInstance (searchString: String) : FlickrSearchResultFragment {
            val bundle = Bundle ()
            bundle.putString("searchKey", searchString)
            val flickrSearchResultFragment = FlickrSearchResultFragment()
            flickrSearchResultFragment.arguments = bundle
            return flickrSearchResultFragment
        }
    }
}



