package com.nbc.video

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.nbc.video.data.DummyData
import com.nbc.video.databinding.FragmentHomeBinding
import com.nbc.video.network.NetworkDataSource
import com.nbc.video.network.model.video.enums.NetworkVideoChart


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var mContext: Context

    private lateinit var networkDataSource: NetworkDataSource

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //popular
        binding.rlPopular.adapter = HomeAdapter(DummyData.getDummyPopularVideos())
        binding.rlPopular.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        //category
        binding.rlCategory.adapter = HomeAdapter(DummyData.getDummyCategoryVideos())
        binding.rlCategory.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        //channel
        binding.rlChannel.adapter = HomeAdapter(DummyData.getDummyChannelVideos())
        binding.rlChannel.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


        //setRecyclerView()
    }

    //Most Popular 영상 리스트
    /*suspend fun popularVideos() {
        val response = networkDataSource.getVideos(
            chart = NetworkVideoChart.MOST_POPULAR, // MOST_POPULAR,
        )

    }*/

    //리사이클러뷰 레이아웃 설정
    /*fun setRecyclerView() {
        binding.rlPopular.adapter = homeAdapter
        binding.rlPopular.layoutManager =
            LinearLayoutManager(mContext).also {
                it.orientation = LinearLayoutManager.HORIZONTAL
            }

        binding.rlCategory.adapter = homeAdapter
        binding.rlCategory.layoutManager =
            LinearLayoutManager(mContext).also {
                it.orientation = LinearLayoutManager.HORIZONTAL
            }


        binding.rlChannel.adapter = homeAdapter
        binding.rlChannel.layoutManager =
            LinearLayoutManager(mContext).also {
                it.orientation = LinearLayoutManager.HORIZONTAL
            }

    }*/
}