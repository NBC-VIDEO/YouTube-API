package com.nbc.video.presenters.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.nbc.video.AppApplication
import com.nbc.video.databinding.FragmentHomeBinding
import com.nbc.video.network.NetworkDataSource
import com.nbc.video.network.model.SearchResponse
import com.nbc.video.network.model.VideoResponse
import com.nbc.video.network.model.search.enums.NetworkSearchType
import com.nbc.video.presenters.home.model.CategoryVideo
import com.nbc.video.presenters.home.model.ChannelVideo
import com.nbc.video.presenters.home.model.Image
import com.nbc.video.presenters.home.model.PopularVideo
import com.nbc.video.presenters.home.model.Thumbnail
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

        dataMapping()
    }


    //데이터 매핑
    private fun dataMapping() {
        //1. 인기있는 영상 리스트. 지역 코드 가져와야하나?
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val popularResponse = networkDataSource.getVideos(
                    maxResults = 20
                )

                val popular = popularResponse.items.map { it.toPopular() }

                requireActivity().runOnUiThread {
                    binding.rlPopular.adapter = HomeAdapter(popular)
                    binding.rlPopular.layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                }

            } catch (e: Exception) {
                e.printStackTrace()
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
                    maxResults = 20
                ).items.map { it.toCategory() }

                requireActivity().runOnUiThread {
                    binding.rlCategory.adapter = HomeAdapter(categoryVideos)
                    binding.rlCategory.layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


        //3. (카테고리 누르고 나오는 관련된) 채널 리스트
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val category = networkDataSource.getVideoRegionCodeCategories(
                    regionCode = "kr" // 국가 코드 선택: en, kr
                ).items.map { it.snippet.title }

                val channelResponse = networkDataSource.searchVideos(
                    type = NetworkSearchType.CHANNEL, //name은 뭐지
                    query = category[0] //query는 검색. 카테고리 0번쨰를 디폴트 값으로.
                ).items.map { it.toChannel() }

                requireActivity().runOnUiThread {
                    binding.rlChannel.adapter = HomeAdapter(channelResponse)
                    binding.rlChannel.layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }


    //1. Popular 리스트
    fun VideoResponse.toPopular(): PopularVideo =
        PopularVideo(
            thumbnails = Thumbnail(
                default = Image(
                    url = this.snippet.thumbnails["defult"]?.url ?: "",
                    width = this.snippet.thumbnails["defult"]?.width ?: 0,
                    height = this.snippet.thumbnails["defult"]?.height ?: 0
                ),
                medium = Image(
                    url = this.snippet.thumbnails["medium"]?.url ?: "",
                    width = this.snippet.thumbnails["medium"]?.width ?: 0,
                    height = this.snippet.thumbnails["medium"]?.height ?: 0
                ),
                height = Image(
                    url = this.snippet.thumbnails["high"]?.url ?: "",
                    width = this.snippet.thumbnails["high"]?.width ?: 0,
                    height = this.snippet.thumbnails["high"]?.height ?: 0
                )
            ),
            title = this.snippet.title
        )


    //2. Channel 리스트
    fun SearchResponse.toChannel(): ChannelVideo =
        ChannelVideo(
            thumbnails = Thumbnail(
                default = Image(
                    url = this.snippet.thumbnails["defult"]?.url ?: "",
                    width = this.snippet.thumbnails["defult"]?.width ?: 0,
                    height = this.snippet.thumbnails["defult"]?.height ?: 0
                ),
                medium = Image(
                    url = this.snippet.thumbnails["medium"]?.url ?: "",
                    width = this.snippet.thumbnails["medium"]?.width ?: 0,
                    height = this.snippet.thumbnails["medium"]?.height ?: 0
                ),
                height = Image(
                    url = this.snippet.thumbnails["high"]?.url ?: "",
                    width = this.snippet.thumbnails["high"]?.width ?: 0,
                    height = this.snippet.thumbnails["high"]?.height ?: 0
                )
            ),
            title = this.snippet.title, channelId = this.snippet.channelId
        )


    //3. Category에 따른 영상 리스트
    fun VideoResponse.toCategory(): CategoryVideo =
        CategoryVideo(
            thumbnails = Thumbnail(
                default = Image(
                    url = this.snippet.thumbnails["defult"]?.url ?: "", //null인 경우 ""
                    width = this.snippet.thumbnails["defult"]?.width ?: 0,
                    height = this.snippet.thumbnails["defult"]?.height ?: 0

                ),
                medium = Image(
                    url = this.snippet.thumbnails["medium"]?.url ?: "",
                    width = this.snippet.thumbnails["medium"]?.width ?: 0,
                    height = this.snippet.thumbnails["medium"]?.height ?: 0
                ),
                height = Image(
                    url = this.snippet.thumbnails["high"]?.url ?: "",
                    width = this.snippet.thumbnails["high"]?.width ?: 0,
                    height = this.snippet.thumbnails["high"]?.height ?: 0
                )
            ),
            title = this.snippet.title, categoryId = this.snippet.categoryId //카테고리 아이디
        )
}