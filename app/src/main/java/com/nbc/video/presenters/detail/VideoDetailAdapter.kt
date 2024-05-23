package com.nbc.video.presenters.detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nbc.video.databinding.RecyclerviewDetailItemBinding

class VideoDetailAdapter : RecyclerView.Adapter<VideoDetailAdapter.Holder>() {
    private var tags: List<String> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun updateItem(tags: List<String>) {
        this.tags = tags
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = RecyclerviewDetailItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(tags[position])
    }

    override fun getItemCount(): Int {
        return tags.size
    }

    class Holder(private val binding: RecyclerviewDetailItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.apply {
                tvRecyclerviewDetailItem.text = item
            }
        }
    }
}