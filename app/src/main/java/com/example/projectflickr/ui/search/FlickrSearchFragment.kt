package com.example.projectflickr.ui.search

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.example.projectflickr.R
import com.example.projectflickr.utils.BasicUtil
import com.example.projectflickr.utils.showKeyboard
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.flickr_search.view.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FlickrSearchFragment : Fragment() {
    private lateinit var rootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setHasOptionsMenu(false)
    }

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
                BasicUtil.hideSoftKeyboard(activity as Activity, rootView)
                Snackbar.make(
                    rootView,
                    resources.getString(R.string.res_input_require),
                    Snackbar.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }
            if (BasicUtil.isNetworkAvailable(context!!)) {
                activity?.supportFragmentManager?.beginTransaction()?.replace(
                    R.id.lytContainer,
                    FlickrSearchResultFragment.newInstance(searchString)
                )?.addToBackStack(null)?.commit()
            } else {
                BasicUtil.hideSoftKeyboard(activity as Activity, rootView)
                Snackbar.make(
                    rootView,
                    resources.getString(R.string.res_network_unavailable),
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }

        rootView.edt_search_tags.requestFocus()
        rootView.edt_search_tags.showKeyboard()
    }
}



