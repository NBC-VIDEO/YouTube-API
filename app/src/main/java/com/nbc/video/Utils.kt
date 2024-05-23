package com.nbc.video

import java.text.DecimalFormat

fun decimal(number: Int): String? {
    val decimal = DecimalFormat("#,###")
    return decimal.format(number)
}

const val BASE_URL = "https://www.googleapis.com"

const val YOUTUBE_API_KEY = "AIzaSyCO8fwY6vPnbB_wUn3038sr50GjRPhFn9M"