package com.nbc.video.presenters.my

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nbc.video.databinding.RecyclerviewMyVideoItemBinding
import com.nbc.video.presenters.my.model.MyVideo

class MyVideoListAdapter(
    private val onItemClicked: (MyVideo) -> Unit
) :
    ListAdapter<MyVideo, MyVideoListAdapter.MyVideoViewHolder>(diffCallback) {

    class MyVideoViewHolder(
        private val binding: RecyclerviewMyVideoItemBinding,
        private val onItemClicked: (MyVideo) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(myVideo: MyVideo) = with(binding) {
            tvMyTitle.text = myVideo.title
            tvMyViewer.text = "조회수 ${myVideo.views}회"
            tvMyDateTime.text = myVideo.dateTime.run { "$hour:$minute" }
            tvMyDescription.text = myVideo.description

            Glide.with(itemView.context)
                .load(myVideo.thumbnailUrl)
                .into(ivMyThumb)

            binding.root.setOnClickListener {
                onItemClicked(myVideo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVideoViewHolder {
        val view = RecyclerviewMyVideoItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyVideoViewHolder(view, onItemClicked)
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