package com.nbc.video.presenters.search

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.nbc.video.AppApplication
import com.nbc.video.databinding.FragmentSearchBinding
import com.nbc.video.navigateToDetailPage
import com.nbc.video.network.NetworkDataSource
import com.nbc.video.network.model.SearchResponse
import com.nbc.video.network.model.search.enums.NetworkSearchType
import com.nbc.video.presenters.search.model.Search
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.ref.WeakReference
import kotlin.random.Random

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
        setupSearchEditText()
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
        searchAdapter = SearchAdapter {
            navigateToDetailPage(it.id)
        }
        binding.rvSearch.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchAdapter
        }
    }

    private fun setupSearchEditText() {
        binding.etSearch.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                performSearch()
                true
            } else {
                false
            }
        }

        binding.etSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                performSearch()
                true
            } else {
                false
            }
        }
    }

    private fun performSearch() {
        val query = binding.etSearch.text.toString().trim()
        if (query.isNotEmpty()) {
            searchYouTubeVideos(query)
            hideKeyboard(requireActivity())
        }
    }

    private fun searchYouTubeVideos(query: String) {
        try {
            networkDataSource.get()?.let { network ->

                lifecycleScope.launch(Dispatchers.IO) {
                    try {
                        val response = network.searchVideos(
                            query = query, // 검색어
                            type = NetworkSearchType.VIDEO, // 서치 타입
                            maxResults = 30 // 데이터 가져오려는 개수
                        )

                        withContext(Dispatchers.Main) {
                            if (response.items.isNullOrEmpty()) {
                                showNoResultsMessage()
                            } else {
                                searchAdapter.setVideos(response.items.toSearchItem()) // 매핑 작업
                                showResults()
                            }
                        }
                    } catch (e: Exception) {
                        Log.e("SearchFragment", "API call failed", e)
                        withContext(Dispatchers.Main) {
                            showError()
                        }
                    }
                }

            } ?: run {
                Log.e("SearchFragment", "NetworkDataSource is null")
                showError()
            }
        } catch (e: Exception) {
            Log.e("SearchFragment", "Unexpected error", e)
            showError()
        }
    }

    private fun showNoResultsMessage() {
        binding.tvResult.visibility = View.VISIBLE
        binding.rvSearch.visibility = View.GONE
    }

    private fun showResults() {
        binding.tvResult.visibility = View.GONE
        binding.rvSearch.visibility = View.VISIBLE
    }

    private fun showError() {
        binding.tvResult.text = "API 호출에 실패했습니다."
        binding.tvResult.visibility = View.VISIBLE
        binding.rvSearch.visibility = View.GONE
    }

    private fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(activity.window.decorView.applicationWindowToken, 0)
    }
}

// Mapper
private fun List<SearchResponse>.toSearchItem(): List<Search.Item> {
    return map {
        Search.Item(
            title = it.snippet.title,
            views = Random.nextLong(1000, 1000000), // 1000에서 1000000 사이의 랜덤 조회수
            thumbnail = it.snippet.thumbnails["default"]?.url ?: "",
            id = it.id.videoId ?: ""
        )
    }
}
