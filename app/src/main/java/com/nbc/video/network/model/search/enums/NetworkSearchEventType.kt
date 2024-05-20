package com.nbc.video.network.model.search.enums

enum class NetworkSearchEventType {
    COMPLETED, LIVE, UPCOMING
}

fun NetworkSearchEventType.toRequestBody(): String = when (this) {
    NetworkSearchEventType.COMPLETED -> "completed"
    NetworkSearchEventType.LIVE -> "live"
    NetworkSearchEventType.UPCOMING -> "upcoming"
}