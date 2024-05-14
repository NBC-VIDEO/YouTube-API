package com.nbc.video.network.model.search.enums

enum class NetworkSearchVideoLicense {
    ANY, CREATIVE_COMMON, YOUTUBE
}

fun NetworkSearchVideoLicense.toRequestBody(): String = when (this) {
    NetworkSearchVideoLicense.ANY -> "any"
    NetworkSearchVideoLicense.CREATIVE_COMMON -> "creativeCommon"
    NetworkSearchVideoLicense.YOUTUBE -> "youtube"
}