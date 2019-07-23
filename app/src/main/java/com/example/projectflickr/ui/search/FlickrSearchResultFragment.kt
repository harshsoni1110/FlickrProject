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
import com.example.projectflickr.FlickrProjectActivity
import com.example.projectflickr.R
import com.example.projectflickr.models.Photo
import com.example.projectflickr.ui.photoDetail.FlickrImageDetailFragment
import com.google.android.material.snackbar.Snackbar
import com.hootsuite.shipmyid.api.ApiErrorResponse
import com.hootsuite.shipmyid.api.ApiSuccessResponse
import kotlinx.android.synthetic.main.list_of_phots.view.*


//Fragment to display search result
class FlickrSearchResultFragment : Fragment(), FlickImageListAdapter.FlickrImageTapListener {

    //avoiding unnecessary API calls when navigating back
    private var isDestroyViewCalled: Boolean = false
    private lateinit var rootView : View
    private lateinit var flickrSearchResultViewModel : FlickrSearchResultViewModel
    private lateinit var flickImageListAdapter: FlickImageListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as FlickrProjectActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
        if (!isDestroyViewCalled) {
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
                when (it) {
                    is ApiSuccessResponse -> {
                        rootView.progressBarFlickrPhotos.visibility = View.GONE
                        rootView.rcyList.visibility = View.VISIBLE
                        flickrSearchResultViewModel.photos.addAll(it.body.photos.photos)
                        flickImageListAdapter.notifyDataSetChanged()

                    }

                    is ApiErrorResponse -> {
                        rootView.progressBarFlickrPhotos.visibility = View.GONE
                        rootView.rcyList.visibility = View.GONE
                        Snackbar.make(
                            rootView,
                            resources.getString(R.string.something_went_wrong),
                            Snackbar.LENGTH_LONG
                        ).show()
                        Log.d("FAILURE", it.errorMessage)
                    }
                }
            })
        }
    }

    override fun onImageClick(photo: Photo) {
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.lytContainer, FlickrImageDetailFragment.newInstance(photo))?.addToBackStack(null)?.commit()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        isDestroyViewCalled =  true
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



