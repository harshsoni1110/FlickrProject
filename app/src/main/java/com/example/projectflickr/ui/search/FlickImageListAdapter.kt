package com.example.projectflickr.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectflickr.R
import com.example.projectflickr.models.Photo
import com.example.projectflickr.utils.PicassoUtil
import kotlinx.android.synthetic.main.image_list_item.view.*

class FlickImageListAdapter(val mList: ArrayList<Photo>, val listener :FlickrImageTapListener )  : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    interface FlickrImageTapListener {
        fun onImageClick (photo: Photo)
    }
    private class RcFlickImageViewHolder(val view: View) :
        RecyclerView.ViewHolder(view) {
        fun bind(photo: Photo, listener: FlickrImageTapListener) {
            itemView.setOnClickListener {
                listener.onImageClick(photo)
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_list_item, parent, false)
        return RcFlickImageViewHolder(view)
    }

    override fun getItemCount(): Int {
       return mList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val photo = mList[position]
        (holder as RcFlickImageViewHolder).bind(photo, listener)
        holder.itemView.txtTitle.text = photo.title
        val url = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_m.jpg"
        PicassoUtil.getInstance().executePicasso(holder.itemView.context, url, holder.itemView.imageView)
    }
}