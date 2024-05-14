package com.nbc.video.network.model.search.enums

enum class NetworkSearchVideoDuration {
    ANY, LONG, MEDIUM, SHORT
}

fun NetworkSearchVideoDuration.toRequestBody() = when (this) {
    NetworkSearchVideoDuration.ANY -> "any"
    NetworkSearchVideoDuration.LONG -> "long"
    NetworkSearchVideoDuration.MEDIUM -> "medium"
    NetworkSearchVideoDuration.SHORT -> "short"
}