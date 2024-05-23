package com.nbc.video

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import java.text.DecimalFormat

fun decimal(number: Int): String? {
    val decimal = DecimalFormat("#,###")
    return decimal.format(number)
}

fun Fragment.navigateToDetailPage(videoId: String) {
    val bundle = bundleOf("videoID" to videoId)
    findNavController().navigate(R.id.videoDetailFragment, bundle)
}

const val BASE_URL = "https://www.googleapis.com"

const val YOUTUBE_API_KEY = "AIzaSyCO8fwY6vPnbB_wUn3038sr50GjRPhFn9M"