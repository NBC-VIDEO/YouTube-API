package com.nbc.video.network.model.search.enums

enum class NetworkSearchVideoPaidProductPlacement {
    ANY, TRUE
}

fun NetworkSearchVideoPaidProductPlacement.toRequestBody() = when (this) {
    NetworkSearchVideoPaidProductPlacement.ANY -> "any"
    NetworkSearchVideoPaidProductPlacement.TRUE -> "true"
}