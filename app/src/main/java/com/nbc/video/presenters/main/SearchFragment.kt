package com.nbc.video.presenters.main

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.nbc.video.AppApplication
import com.nbc.video.presenters.search.SearchAdapter
import com.nbc.video.databinding.FragmentSearchBinding
import com.nbc.video.network.NetworkDataSource
import com.nbc.video.network.model.SearchResponse
import com.nbc.video.network.model.search.enums.NetworkSearchType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var searchAdapter: SearchAdapter

    private lateinit var networkDataSource: WeakReference<NetworkDataSource>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNetwork()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
//        searchYouTubeVideos("쌍수 후 관리법")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        networkDataSource.clear()
    }

    private fun initNetwork() {
        val appApplication = requireActivity().application as AppApplication
        networkDataSource = WeakReference(appApplication.networkDataSource)
    }

    private fun setupRecyclerView() {
        searchAdapter = SearchAdapter()
        binding.rvSearch.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchAdapter
        }
    }


    private fun searchYouTubeVideos(query: String) = try {
        networkDataSource.get()?.let { network ->

            lifecycleScope.launch(Dispatchers.IO) {
                val response = network.searchVideos(
                    query = query,
                    type = NetworkSearchType.VIDEO
                )

//                searchAdapter.setVideos(response)
            }

        }
    } catch (e: Exception) {
        Log.e("SearchFragment", "API call failed", e)
    }

    private fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(activity.window.decorView.applicationWindowToken, 0)
    }
}

private fun SearchResponse.toSearchItem() {

}