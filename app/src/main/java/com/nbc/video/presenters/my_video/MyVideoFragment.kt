package com.nbc.video.presenters.my_video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.nbc.video.databinding.FragmentMyVideoBinding

class MyVideoFragment : Fragment() {
    private var _binding: FragmentMyVideoBinding? = null
    private val binding get() = _binding!!

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
//        setProfileView()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        binding.rvMyMain.adapter = MyVideoListAdapter()
        binding.rvMyMain.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setProfileView() {
        binding.tvMyName.text = ""
        binding.tvMyIntroduction.text = ""
        Glide.with(requireContext()).load("URL").into(binding.ivMyProfile)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}