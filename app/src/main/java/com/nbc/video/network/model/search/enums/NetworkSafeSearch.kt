package com.nbc.video.network.model.search.enums

enum class NetworkSafeSearch {
    MODERATE, NONE, STRICT
}

fun NetworkSafeSearch.toRequestBody() = when (this) {
    NetworkSafeSearch.MODERATE -> "moderate"
    NetworkSafeSearch.NONE -> "none"
    NetworkSafeSearch.STRICT -> "strict"
}