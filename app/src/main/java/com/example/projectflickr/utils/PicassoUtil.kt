package com.example.projectflickr.utils

import android.content.Context
import android.widget.ImageView
import com.squareup.picasso.Picasso

class PicassoUtil{
    private var picasso: Picasso? = null


    private fun initPicasso (context: Context) {
        if (picasso == null) {
            val picassoBuilder = Picasso.Builder(context.applicationContext)
            picasso = picassoBuilder.build()
        }

    }

    fun executePicasso (context: Context, imageURL: String, imageView: ImageView,   size:Int) {
        initPicasso(context)
        picasso?.load(imageURL)?.resize(size, size)?.into(imageView)
    }
    fun executePicasso (context: Context, imageURL: String, imageView: ImageView) {
        initPicasso(context)
        picasso?.load(imageURL)?.into(imageView)
    }

    companion object {
        fun getInstance () : PicassoUtil {
            return PicassoUtil()
        }

    }



}