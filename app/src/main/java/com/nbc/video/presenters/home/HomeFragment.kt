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
import com.nbc.video.presenters.home.data.HomeDummyData
import com.nbc.video.databinding.FragmentHomeBinding
import com.nbc.video.network.NetworkDataSource
import com.nbc.video.network.model.SearchResponse
import com.nbc.video.network.model.VideoResponse
import com.nbc.video.network.model.search.enums.NetworkSearchType
import com.nbc.video.network.model.video.enums.NetworkVideoChart
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

    private lateinit var videoResponse: VideoResponse
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

        setRecyclerview()

        init()
    }

    //리사이클러뷰 설정
    private fun setRecyclerview() {
        //popular
        binding.rlPopular.adapter = HomeAdapter(HomeDummyData.getDummyPopularVideos())
        binding.rlPopular.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        //category
        binding.rlCategory.adapter = HomeAdapter(HomeDummyData.getDummyCategoryVideos())
        binding.rlCategory.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        //channel
        binding.rlChannel.adapter = HomeAdapter(HomeDummyData.getDummyChannelVideos())
        binding.rlChannel.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

    }


    //데이터 매핑
    private fun init() {
        //1. 인기있는 영상 리스트. 지역 코드 가져와야하나?
        lifecycleScope.launch(Dispatchers.IO) {
            val response = networkDataSource.getVideos(
                chart = NetworkVideoChart.MOST_POPULAR // MOST_POPULAR
            )

            //여기서 타이틀을 가져오는게 맞나? 타이틀을 포함한 리스트 생성?
            val popular = response.items.map { it.snippet.title }

            val popularResponse = networkDataSource.getVideos( //동영상 id와 지역 코드
                id = NetworkVideoChart.MOST_POPULAR.name,
                regionCode = popular[0] //초기화값 지정
            )
            val pupularmap = popularResponse.items.map { it.toPopular() }

            binding.rlPopular.adapter = HomeAdapter(pupularmap)
            binding.rlPopular.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        //2. (국가 선택에 따른) 카테고리 리스트를 가져와서 카테고리 리스트 생성하기 (참고본)
        lifecycleScope.launch(Dispatchers.IO) {
            val response = networkDataSource.getVideoRegionCodeCategories(
                regionCode = "kr" // 국가 코드 선택: en, kr
            )

            //스냅핏의 타이틀을 가진 리스트를 생성함?
            val category = response.items.map { it.snippet.title }

            val channelResponse = networkDataSource.searchVideos(
                type = NetworkSearchType.CHANNEL,
                query = category[3] //q 매개변수는 검색할 검색어를 지정,  디폴드 값을 정해준 것
            )
            val channel =
                channelResponse.items.map { it.toChannel() } // toChannel이라는 함수를 활용해 리스트를 생성함?

            binding.rlCategory.adapter = HomeAdapter(channel)
            binding.rlCategory.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }


        //3. (카테고리 누르고 나오는) 채널 리스트
        lifecycleScope.launch(Dispatchers.IO) {
            val response = networkDataSource.getVideos(
                videoCategoryId = "17" // 카테고리 선택
            )

            //스닙팻의 지역별 카테고리 아이디를 포함한 리스트를 생성하는건가?
            val channel = response.items.map { it.snippet.categoryId }

            //여기서 categoryId를 가져와야하나?? getVideos에서 가져와서 안해도 되나?  (디폴트 카테고리를 정해야하니까)
            val channelResponse = networkDataSource.searchVideos(
                videoCategoryId = NetworkSearchType.VIDEO.name, //name은 뭐지
                query = channel[4]
            )

            //이거 안됨
            val channelmap = channelResponse.items.map { it.toChannel() }

            binding.rlChannel.adapter = HomeAdapter(channelmap)
            binding.rlChannel.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        }


    }


    //1. Popular 리스트 (지역코드를 가진?)
    // VideoResponse 와 PopularVideo 를 연결함?
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


    //2. Channel 리스트  (참고본)
    // Q. 카테고리 아이디를 가져와야하지 않나?
    // Q. searchVideos 함수에서 channelType와 channelId 비슷한데 둘중 무엇인가 ?
    // SearchResponse에는 categoryId가 없음!
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
    // Q. 카테고리 아이디를 필요하겠죠?
    // Q. 채널 썸네일은 어딨지..
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