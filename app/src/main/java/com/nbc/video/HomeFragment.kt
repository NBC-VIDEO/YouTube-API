package com.nbc.video

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.api.services.youtube.YouTube
import com.nbc.video.data.Home
import com.nbc.video.databinding.FragmentHomeBinding



class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var mContext: Context
    private lateinit var homeAdapter: HomeAdapter

    private var homeItems: ArrayList<Home> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    //리사이클러뷰 레이아웃 설정
    fun setRecyclerView() {
        binding.rlPopular.adapter = homeAdapter
        binding.rlPopular.layoutManager =
            LinearLayoutManager(context).also { it.orientation = LinearLayoutManager.HORIZONTAL }

        binding.rlCategory.adapter = homeAdapter
        binding.rlCategory.layoutManager =
            LinearLayoutManager(context).also { it.orientation = LinearLayoutManager.HORIZONTAL }

        binding.rlChannel.adapter = homeAdapter
        binding.rlChannel.layoutManager =
            LinearLayoutManager(context).also { it.orientation = LinearLayoutManager.HORIZONTAL }

    }
}