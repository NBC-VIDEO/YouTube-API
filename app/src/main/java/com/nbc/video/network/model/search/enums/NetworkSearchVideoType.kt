package com.nbc.video.network.model.search.enums

enum class NetworkSearchVideoType {
    ANY, EPISODE, MOVIE
}

fun NetworkSearchVideoType.toRequestBody() = when (this) {
    NetworkSearchVideoType.ANY -> "any"
    NetworkSearchVideoType.EPISODE -> "episode"
    NetworkSearchVideoType.MOVIE -> "movie"
}