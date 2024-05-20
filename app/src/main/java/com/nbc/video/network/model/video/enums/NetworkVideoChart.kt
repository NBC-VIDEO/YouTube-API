package com.nbc.video.network.model.video.enums

enum class NetworkVideoChart {
    MOST_POPULAR
}


fun NetworkVideoChart.toRequestBody(): String =
    when (this) {
        NetworkVideoChart.MOST_POPULAR -> "most_popular"
    }
