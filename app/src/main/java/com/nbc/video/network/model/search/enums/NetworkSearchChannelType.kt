package com.nbc.video.network.model.search.enums

enum class NetworkSearchChannelType {
    ANY, SHOW
}

fun NetworkSearchChannelType.toRequestBody(): String =
    when (this) {
        NetworkSearchChannelType.ANY -> "any"
        NetworkSearchChannelType.SHOW -> "show"
    }