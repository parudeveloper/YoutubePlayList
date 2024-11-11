package com.youtubeplaylist.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.youtubeplaylist.R
import com.youtubeplaylist.data.VideoItem

class VideoAdapter(private val onItemClick: (String) -> Unit) : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    private var videoList = mutableListOf<VideoItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val videoItem = videoList[position]
        holder.titleTextView.text = videoItem.snippet.title
        holder.descriptionTextView.text = videoItem.snippet.description
        Picasso.get().load(videoItem.snippet.thumbnails.medium.url).into(holder.thumbnailImageView)
        // Set click listener to open the video in the VideoPlayerActivity
        holder.itemView.setOnClickListener {
            // Pass the videoId to the VideoPlayerActivity
            onItemClick(videoItem.id.videoId)
        }
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    fun submitList(list: List<VideoItem>) {
        videoList.clear()
        videoList.addAll(list)
        notifyDataSetChanged()
    }

    inner class VideoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = view.findViewById(R.id.descriptionTextView)
        val thumbnailImageView: ImageView = view.findViewById(R.id.thumbnailImageView)
    }
}