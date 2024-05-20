package com.nbc.video.network.model.search.enums

enum class NetworkSearchVideoSyndicated {
    ANY, TRUE
}

fun NetworkSearchVideoSyndicated.toRequestBody() = when (this) {
    NetworkSearchVideoSyndicated.ANY -> "any"
    NetworkSearchVideoSyndicated.TRUE -> "true"
}