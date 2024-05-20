package com.nbc.video.presenters.my_video

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nbc.video.databinding.RecyclerviewMyVideoItemBinding
import com.nbc.video.presenters.my_video.model.MyVideo

class MyVideoListAdapter :
    ListAdapter<MyVideo, MyVideoListAdapter.MyVideoViewHolder>(diffCallback) {

    class MyVideoViewHolder(
        private val binding: RecyclerviewMyVideoItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(myVideo: MyVideo) = with(binding) {
            tvMyTitle.text = myVideo.title
            tvMyViewer.text = myVideo.views.toString()
            tvMyDateTime.text = myVideo.dateTime.toString()
            tvMyDescription.text = myVideo.description

            Glide.with(itemView.context)
                .load(myVideo.thumbnail.medium.url)
                .into(ivMyThumb)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVideoViewHolder {
        val view = RecyclerviewMyVideoItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyVideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyVideoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<MyVideo>() {
            override fun areItemsTheSame(oldItem: MyVideo, newItem: MyVideo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MyVideo, newItem: MyVideo): Boolean {
                return oldItem == newItem
            }
        }
    }
}