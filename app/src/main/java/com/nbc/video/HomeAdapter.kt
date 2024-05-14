package com.nbc.video

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.nbc.video.data.Home
import com.nbc.video.data.Item
import com.nbc.video.databinding.VideoItemBinding

class HomeAdapter(private val mContext: Context) :
    RecyclerView.Adapter<HomeAdapter.VideoViewHolder>() {
    //비디오 목록
    var videos = ArrayList<Home>()
    var items = ArrayList<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding = VideoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        var currentVideo = videos[position]
        var item = items[position]

        holder.iv_video.setImageURI(item.thumbnailUrl.toUri()) //이거 맞나?
        holder.tv_title.text = item.title

    }

    override fun getItemCount(): Int = videos.size

    inner class VideoViewHolder(binding: VideoItemBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {
        var iv_video: ImageView = binding.ivVideo
        var tv_title: TextView = binding.tvTitle
        override fun onClick(v: View?) {
            val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return
        }


    }

}