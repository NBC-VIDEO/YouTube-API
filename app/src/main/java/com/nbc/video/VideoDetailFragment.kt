package com.nbc.video

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.nbc.video.databinding.FragmentVideoDetailBinding

private const val ARG_PARAM1 = "param1"     // newInstance로 동영상 고유 ID값 받기

class VideoDetailFragment : Fragment() {
    private var param1: String? = null
    private lateinit var _binding: FragmentVideoDetailBinding
    private val binding get() = _binding
    private val userDetailData = DetailDummyData.user
    private val viewModel: VideoDetailViewModel by viewModels { VideoDetailViewModel.viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)       // param1 : 동영상 고유 ID
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // param1로 서버에 요청 보내기

        initRecyclerview()
        initData()

        binding.btnDetailGood.setOnClickListener {
            // isLiked = true 로 변경 & 내부에 저장
            val newVideo = videoDetailEntity(
                id = userDetailData.items.id,
                channelId = userDetailData.items.snippet.channelId
            )
            viewModel.insertVideo(newVideo)
            switchIsLiked(newVideo.channelId)
        }

        viewModel.getAllVideos().observe(viewLifecycleOwner) {
            // 데이터 변경시 처리
        }
    }

    // 리사이클러뷰 연결
    @SuppressLint("NotifyDataSetChanged")
    private fun initRecyclerview() {
        val userDetailData = userDetailData.items.snippet
        val videoDetailAdapter = VideoDetailAdapter()
        binding.detailRecyclerview.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = videoDetailAdapter
            videoDetailAdapter.data = userDetailData
            videoDetailAdapter.notifyDataSetChanged()
        }
    }

    private fun initData() {
        binding.apply {
            Glide.with(requireContext())
                .load(userDetailData.items.snippet.thumbnails.default.url)
                .into(ivDetailThumbnail)
            tvDetailTitle.text = userDetailData.items.snippet.title
            tvDetailViewCount.text = Decimal(userDetailData.items.statistics.viewCount)
            tvDetailPublish.text = userDetailData.items.snippet.publishedAt
            tvDetailChannelName.text = userDetailData.items.snippet.channelTitle
            tvDetailChannelId.text = userDetailData.items.snippet.channelId
            // channel thumbnail이 없다???????
        }
    }

    // 좋아요 표시 & Entity 추가 비동기 함수
    private fun switchIsLiked(channelId: String) {
        var video = viewModel.getAllVideos().value?.firstOrNull { it.channelId == channelId }
        if (video == null) {
            video = videoDetailEntity(
                id = userDetailData.items.id,
                channelId = channelId,
                isLiked = true
            )
            viewModel.insertVideo(video)
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

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            VideoDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}