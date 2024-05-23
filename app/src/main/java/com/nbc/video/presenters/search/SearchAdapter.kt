package com.nbc.video.presenters.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nbc.video.R
import com.nbc.video.databinding.ItemVideoBinding
import com.nbc.video.presenters.search.model.Search

class SearchAdapter(
    private val onVideoClick: (Search.Item) -> Unit,
) : RecyclerView.Adapter<SearchAdapter.VideoViewHolder>() {
    private var videos: List<Search.Item> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding = ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoViewHolder(binding, onVideoClick)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(videos[position])
    }

    override fun getItemCount(): Int = videos.size

    fun setVideos(videos: List<Search.Item>) {
        this.videos = videos
        notifyDataSetChanged()
    }

    class VideoViewHolder(
        private val binding: ItemVideoBinding,
        private val onVideoClick: (Search.Item) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(searchItem: Search.Item) { // 항목 바인딩
            binding.tvViews.text = searchItem.views.toString()
            binding.tvTitle.text = searchItem.title

            Glide.with(binding.root.context) // Glide를 사용하여 이미지 로딩
                .load(searchItem.thumbnail)
                .placeholder(R.drawable.ic_reload) // 이미지 로드 실패 시 표시될 이미지
                .into(binding.imgItem)
            binding.root.setOnClickListener {
                onVideoClick(searchItem)
            }
        }
    }
}
