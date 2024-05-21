package com.nbc.video.presenters.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.nbc.video.databinding.VideoItemBinding
import com.nbc.video.presenters.home.model.CategoryVideo
import com.nbc.video.presenters.home.model.ChannelVideo
import com.nbc.video.presenters.home.model.PopularVideo

class HomeAdapter<T> : RecyclerView.Adapter<HomeAdapter.VideoViewHolder>() {

    //처음에 비어있는 리스트
    private var items: List<T> = emptyList()

    //카테고리 선택후 리스트 업데이트
    fun updateItems(items: List<T>) {
        this.items = items
        notifyDataSetChanged()
    }

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
                        .apply(RequestOptions.bitmapTransform(RoundedCorners(20))) //둥글게 처리
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