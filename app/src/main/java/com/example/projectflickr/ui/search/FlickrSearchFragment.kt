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
import com.hootsuite.shipmyid.api.ApiErrorResponse
import com.hootsuite.shipmyid.api.ApiSuccessResponse
import kotlinx.android.synthetic.main.flickr_search.view.*
import kotlinx.android.synthetic.main.list_of_phots.view.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FlickrSearchFragment : Fragment() {
    private lateinit var rootView : View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!(::rootView.isInitialized)) {
            rootView = inflater.inflate(R.layout.flickr_search, container, false)
        }
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rootView.btn_search.setOnClickListener {
            val searchString = rootView.edt_search_tags.editableText.toString()
            if (searchString == "") {
                return@setOnClickListener
            }
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.lytContainer, FlickrSearchResultFragment.newInstance(searchString))?.addToBackStack(null)?.commit()
        }
    }

}



