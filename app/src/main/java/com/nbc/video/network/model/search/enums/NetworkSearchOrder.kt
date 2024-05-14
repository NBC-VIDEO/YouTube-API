package com.nbc.video.network.model.search.enums

enum class NetworkSearchOrder {
    DATE, RATING, RELEVANCE, TITLE, VIDEO_COUNT, VIEW_COUNT
}

fun NetworkSearchOrder.toRequestBody() = when (this) {
    NetworkSearchOrder.DATE -> "date"
    NetworkSearchOrder.RATING -> "rating"
    NetworkSearchOrder.RELEVANCE -> "relevance"
    NetworkSearchOrder.TITLE -> "title"
    NetworkSearchOrder.VIDEO_COUNT -> "videoCount"
    NetworkSearchOrder.VIEW_COUNT -> "viewCount"
}