package com.nbc.video.presenters.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.nbc.video.presenters.home.model.CategoryVideo
import com.nbc.video.presenters.home.model.ChannelVideo
import com.nbc.video.presenters.home.model.PopularVideo
import com.nbc.video.databinding.VideoItemBinding

class HomeAdapter<T>(
    private val items: List<T>,
) : RecyclerView.Adapter<HomeAdapter.VideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding = VideoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(items[position])
    }


    override fun getItemCount(): Int {
        return items.size
    }

    class VideoViewHolder(private val binding: VideoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun <T> bind(item: T) {
            when (item) {
                is PopularVideo -> {
                    binding.tvTitle.text = item.title
                    Glide.with(itemView.context)
                        .load(item.thumbnails.medium.url)
                        .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
                        .into(binding.ivVideo)
                }

                is CategoryVideo -> {
                    binding.tvTitle.text = item.title
                    Glide.with(itemView.context)
                        .load(item.thumbnails.medium.url)
                        .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
                        .into(binding.ivVideo)
                }

                is ChannelVideo -> {
                    binding.tvTitle.text = item.title
                    Glide.with(itemView.context)
                        .load(item.thumbnails.medium.url)
                        .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
                        .into(binding.ivVideo)
                }
            }
        }
    }

}