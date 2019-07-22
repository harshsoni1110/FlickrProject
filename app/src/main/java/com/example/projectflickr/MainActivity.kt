package com.example.projectflickr

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.projectflickr.ui.search.FlickrSearchFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setSupportActionBar(toolbar)
        supportFragmentManager.beginTransaction().replace(R.id.lytContainer, FlickrSearchFragment()).commit()
    }

/*
    fun setToolBarTextAndUp (text: String, isUpNav: Boolean){
        toolbar.toolbarTitle.text = text
        supportActionBar?.setDisplayHomeAsUpEnabled(isUpNav)
    }
*/

/*    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                false
            }
            else -> super.onOptionsItemSelected(item)
        }
    }*/


}
