package com.nbc.video

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.nbc.video.data.Home
import com.nbc.video.databinding.FragmentHomeBinding
import com.nbc.video.network.NetworkDataSource
import com.nbc.video.network.model.YouTubeResponse
import com.nbc.video.network.model.video.enums.NetworkVideoChart
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var mContext: Context
    private var homeAdapter: HomeAdapter? = null
    private var homeItems: ArrayList<Home> = ArrayList()

    private lateinit var networkDataSource: NetworkDataSource


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        setRecyclerView()

    }

    //Most Popular 영상 리스트
    suspend fun popularVideos() {
        val response = networkDataSource.getVideos(
            chart = NetworkVideoChart.MOST_POPULAR, // MOST_POPULAR,
        )
view.apply {

}

    }
    /*private fun fetchImageResults(query: String) {
        apiService.image_search(Constants.AUTH_HEADER, query, "recency", 1, 80)
            ?.enqueue(object : Callback<ImageModel?> {
                override fun onResponse(call: Call<ImageModel?>, response: Response<ImageModel?>) {
                    response.body()?.meta?.let { meta ->
                        if (meta.totalCount > 0) {
                            response.body()!!.documents.forEach { document ->
                                val title = document.displaySitename
                                val datetime = document.datetime
                                val url = document.thumbnailUrl
                                resItems.add(SearchItemModel(title, datetime, url))
                            }
                        }
                    }
                    adapter.items = resItems
                    adapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<ImageModel?>, t: Throwable) {
                    Log.e("#jblee", "onFailure: ${t.message}")
                }
            })
    }*/


    //리사이클러뷰 레이아웃 설정
    fun setRecyclerView() {

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

    }
}