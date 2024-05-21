package com.nbc.video.presenters.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nbc.video.AppApplication
import com.nbc.video.R
import com.nbc.video.databinding.FragmentHomeBinding
import com.nbc.video.network.NetworkDataSource
import com.nbc.video.network.model.search.enums.NetworkSearchType
import com.nbc.video.presenters.home.model.CategoryVideo
import com.nbc.video.presenters.home.model.ChannelVideo
import com.nbc.video.presenters.home.model.PopularVideo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var mContext: Context
    private lateinit var networkDataSource: NetworkDataSource

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val application = (requireActivity().application) as AppApplication
        networkDataSource = application.networkDataSource
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

        val spinner = binding.spinner

        binding.rlPopular.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rlPopular.adapter = HomeAdapter<PopularVideo> {
            val bundle = bundleOf("videoID" to it)
            findNavController().navigate(R.id.action_homeFragment_to_videoDetailFragment, bundle)
        }

        binding.rlCategory.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rlCategory.adapter = HomeAdapter<CategoryVideo> {
            val bundle = bundleOf("videoID" to it)
            findNavController().navigate(R.id.action_homeFragment_to_videoDetailFragment, bundle)
        }

        binding.rlChannel.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rlChannel.adapter = HomeAdapter<ChannelVideo> {

        }


        lifecycleScope.launch {
            try {
                //2. 카테고리 고른 후 영상 리스트 가져오기
                val response = networkDataSource.getVideoRegionCodeCategories(
                    regionCode = "kr" // 국가 코드 선택: en, kr
                )
                val spinnerList = response.items.map { it.snippet.title }   //카테고리 (타이틀)이름으로 리스트 생성

                //스피너 레이아웃과 스피너 리스트 연결
                spinner.adapter =
                    SpinnerAdapter(requireContext(), R.layout.item_spinner, spinnerList)

                //스피너 클릭했을 때 리스너
                spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    //카테고리 선택했을 때
                    override fun onItemSelected(
                        parent: AdapterView<*>?, view: View?, position: Int,
                        id: Long
                    ) {
                        lifecycleScope.launch {
                            //2. 카테고리 관련 영상 리스트
                            (binding.rlCategory.adapter as? HomeAdapter<CategoryVideo>)?.let { adapter ->
                                val categoryVideos = networkDataSource.getVideos(
                                    videoCategoryId = response.items[position].id,
                                    maxResults = 20
                                ).items.map { it.toCategory() }
                                adapter.updateItems(categoryVideos) //리스트 업데이트
                            }

                            //2. 카테고리 관련 채널 리스트
                            (binding.rlChannel.adapter as? HomeAdapter<ChannelVideo>)?.let { adapter ->
                                val category = networkDataSource.getVideoRegionCodeCategories(
                                    regionCode = "kr" // 국가 코드 선택: en, kr
                                ).items.map { it.snippet.title }

                                val channelResponse = networkDataSource.searchVideos(
                                    type = NetworkSearchType.CHANNEL,
                                    query = category[position],
                                    maxResults = 20
                                ).items.map { it.toChannel() }
                                adapter.updateItems(channelResponse)
                            }
                        }

                        //카테고리 클릭하면 카테고리 (타이틀)이름 Toast 뜸
                        val value = spinner.getItemAtPosition(position).toString()
                        Toast.makeText(requireContext(), value, Toast.LENGTH_SHORT).show()
                    }

                    //카테고리 선택하지 않았을 때
                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }
                }

            } catch (e: Exception) {
                Log.e("error", "카테고리 선택 후 리스트 불러오기 실패")
            }
        }

        //데이터 매핑
        dataMapping()
    }


    //데이터 매핑
    fun dataMapping() {
        //1. 인기있는 영상 리스트.
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val popularResponse = networkDataSource.getVideos(
                    maxResults = 10 //최대 영상 20개
                )

                val popular = popularResponse.items.map { it.toPopular() }

                requireActivity().runOnUiThread {
                    (binding.rlPopular.adapter as? HomeAdapter<PopularVideo>)?.apply {
                        updateItems(popular)
                    }
                }

            } catch (e: Exception) {
                Log.e("error", "인기 있는 영상 리스트 불러오기 실패")
            }

        }


        //2. (국가 선택에 따른) 카테고리 리스트가져와서 카테고리 영상 리스트
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = networkDataSource.getVideoRegionCodeCategories(
                    regionCode = "kr" // 국가 코드 선택: en, kr
                )

                val categoryVideos = networkDataSource.getVideos(
                    videoCategoryId = response.items.first().id,
                    maxResults = 10
                ).items.map { it.toCategory() }

                requireActivity().runOnUiThread {
                    (binding.rlCategory.adapter as? HomeAdapter<CategoryVideo>)?.apply {
                        updateItems(categoryVideos)
                    }
                }

            } catch (e: Exception) {
                Log.e("error", "카테고리 선택 후 관련 영상 리스트 불러오기 실패")
            }
        }


        //3. (카테고리 누르고 나오는 관련된) 채널 리스트
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val category = networkDataSource.getVideoRegionCodeCategories(
                    regionCode = "kr" // 국가 코드 선택: en, kr
                ).items.map { it.snippet.title }

                val channelResponse = networkDataSource.searchVideos(
                    type = NetworkSearchType.CHANNEL,
                    query = category[0]     //query는 검색. 카테고리 0번째를 디폴트 값으로.
                ).items.map { it.toChannel() }

                requireActivity().runOnUiThread {
                    (binding.rlChannel.adapter as? HomeAdapter<ChannelVideo>)?.apply {
                        updateItems(channelResponse)
                    }
                }

            } catch (e: Exception) {
                Log.e("error", "카테고리 선택 후 채널 리스트 불러오기 실패")
            }
        }

    }

}
