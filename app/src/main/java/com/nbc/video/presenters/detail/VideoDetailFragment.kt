package com.nbc.video.presenters.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.nbc.video.database.model.VideoDetailEntity
import com.nbc.video.databinding.FragmentVideoDetailBinding
import com.nbc.video.decimal
import com.nbc.video.network.model.VideoResponse
import com.nbc.video.presenters.detail.model.StatisticsModel
import com.nbc.video.presenters.detail.model.VideoDetailsModel

class VideoDetailFragment : Fragment() {
    private lateinit var _binding: FragmentVideoDetailBinding
    private val binding get() = _binding
    private val viewModel: VideoDetailViewModel by viewModels { VideoDetailViewModel.viewModelFactory }
    private lateinit var videoDetailAdapter: VideoDetailAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentVideoDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val input = requireArguments().getString("videoID")

        if (input != null) {
            viewModel.updateVideoDetailModel(input)
        }
        initRecyclerview()

        binding.btnDetailGood.setOnClickListener {
            // isLiked = true 로 변경 & 내부에 저장
            viewModel.videoDetailModelLiveData.value?.items?.firstOrNull()?.toEntity(true)
                ?.let { newVideo ->
                    viewModel.insertVideo(newVideo)
                    switchIsLiked(newVideo.channelId)
                }
        }

        viewModel.getAllVideos().observe(viewLifecycleOwner) {
            // 데이터 변경시 처리
        }

        viewModel.videoDetailModelLiveData.observe(viewLifecycleOwner) { videoDetailsModel ->
            videoDetailAdapter.updateItem(
                videoDetailsModel.items.firstOrNull()?.snippet?.tags ?: emptyList()
            )
            initData(videoDetailsModel)
        }
    }

    // 리사이클러뷰 연결
    @SuppressLint("NotifyDataSetChanged")
    private fun initRecyclerview() {
        videoDetailAdapter = VideoDetailAdapter()
        binding.detailRecyclerview.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = videoDetailAdapter
        }
    }

    private fun initData(videoDetailsModel: VideoDetailsModel) {
        binding.apply {
            val thumbnail = videoDetailsModel.items.firstOrNull()?.snippet?.thumbnails?.default
            if (thumbnail != null) {
                Glide.with(requireContext())
                    .load(thumbnail.url)
                    .override(thumbnail.width, thumbnail.height)
                    .into(ivDetailThumbnail)
            }
            tvDetailTitle.text = videoDetailsModel.items.firstOrNull()?.snippet?.title
            tvDetailViewCount.text =
                decimal(videoDetailsModel.items.firstOrNull()?.statistics?.viewCount!!)
            tvDetailPublish.text =
                videoDetailsModel.items.firstOrNull()?.snippet?.publishedAt?.replace("T", " ")
                    ?.substring(0 until 19)
            tvDetailChannelName.text = videoDetailsModel.items.firstOrNull()?.snippet?.channelTitle
            tvDetailChannelId.text =
                videoDetailsModel.items.firstOrNull()?.snippet?.channelId?.replace("_", "-")
            Glide.with(requireContext())
                .load(videoDetailsModel.items.firstOrNull()?.channelSnippet?.thumbnails?.default?.url)
                .into(ivDetailChannelImage)
        }
    }

    // 좋아요 표시 & Entity 추가 비동기 함수
    private fun switchIsLiked(channelId: String) {
        var video = viewModel.getAllVideos().value?.firstOrNull { it.channelId == channelId }
        if (video == null) {
            video = viewModel.videoDetailModelLiveData.value?.items?.firstOrNull()?.toEntity(true)
            if (video != null) {
                viewModel.insertVideo(video)
            }
        } else {
            val isLikedBefore = video.isLiked
            video.isLiked = !video.isLiked
            viewModel.updateIsLiked(video)

            // isLiked 가 true -> false 될 때
            if (isLikedBefore && !video.isLiked) {
                viewModel.deleteVideo(video)
            }
        }
    }

    // 공유 기능
    private fun shareVideo() {}
}

// Mapper
private fun VideoResponse.asExtermianlModel(): StatisticsModel {
    return StatisticsModel(
        viewCount = statistics.viewCount.toInt(),
    )
}