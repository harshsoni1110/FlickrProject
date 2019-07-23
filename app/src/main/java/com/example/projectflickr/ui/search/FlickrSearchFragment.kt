package com.example.projectflickr.ui.search

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projectflickr.FlickrProjectActivity
import com.example.projectflickr.R
import com.example.projectflickr.utils.BasicUtil
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.flickr_search.view.*


//Fragment to search
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

            //No text for search
            if (searchString == "") {
                BasicUtil.hideSoftKeyboard(activity as Activity, rootView)
                Snackbar.make(rootView , resources.getString(R.string.res_input_require), Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (BasicUtil.isNetworkAvailable(context!!)) {
                //Result screen navigation
                activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.lytContainer, FlickrSearchResultFragment.newInstance(searchString))?.addToBackStack(null)?.commit()
            }
            else {
                //Network unavailable, cannot do API call
                BasicUtil.hideSoftKeyboard(activity as Activity, rootView)
                Snackbar.make(rootView , resources.getString(R.string.res_network_unavailable), Snackbar.LENGTH_LONG).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as FlickrProjectActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)   //Resetting back button
    }
}



