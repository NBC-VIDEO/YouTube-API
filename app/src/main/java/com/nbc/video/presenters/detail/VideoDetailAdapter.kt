package com.nbc.video.presenters.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nbc.video.presenters.detail.model.VideoSnippetModel
import com.nbc.video.databinding.RecyclerviewDetailItemBinding

class VideoDetailAdapter : RecyclerView.Adapter<VideoDetailAdapter.Holder>() {
    lateinit var data : VideoSnippetModel
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = RecyclerviewDetailItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val currentItem = data.tags[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return data.tags.size
    }

    class Holder(private val binding: RecyclerviewDetailItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.apply {
                tvRecyclerviewDetailItem.text = item
            }
        }
    }
}