package com.nbc.video.network.model.search.enums

enum class NetworkSearchType {
    CHANNEL, PLAYLIST, VIDEO
}

fun NetworkSearchType.toRequestBody() = when (this) {
    NetworkSearchType.CHANNEL -> "channel"
    NetworkSearchType.PLAYLIST -> "playlist"
    NetworkSearchType.VIDEO -> "video"

}