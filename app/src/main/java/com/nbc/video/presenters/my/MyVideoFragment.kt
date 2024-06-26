package com.nbc.video.presenters.my

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.nbc.video.databinding.FragmentMyVideoBinding
import com.nbc.video.navigateToDetailPage
import kotlinx.coroutines.launch

class MyVideoFragment : Fragment() {
    private var _binding: FragmentMyVideoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MyVideoViewModel by viewModels { MyVideoViewModel.viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMyVideoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setProfileView()
        setRecyclerView()
        observeRecyclerViewItem()
    }

    private fun setRecyclerView() {
        binding.rvMyMain.adapter = MyVideoListAdapter {
            navigateToDetailPage(it.id)
        }
        binding.rvMyMain.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setProfileView() {
        binding.layoutProfile.tvMyName.text = "김민준"
        Glide.with(requireContext())
            .load("https://img.freepik.com/premium-vector/man-avatar-profile-picture-vector-illustration_268834-538.jpg?w=826")
            .into(binding.layoutProfile.ivMyProfile)
    }

    private fun observeRecyclerViewItem() = lifecycleScope.launch {
        viewModel.likeVideos.observe(viewLifecycleOwner) {
            val adapter = (binding.rvMyMain.adapter as? MyVideoListAdapter) ?: return@observe
            adapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}