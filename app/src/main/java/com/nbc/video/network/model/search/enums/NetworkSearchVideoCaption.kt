package com.nbc.video.network.model.search.enums

enum class NetworkSearchVideoCaption {
    ANY, CLOSED_CAPTION, NONE
}

fun NetworkSearchVideoCaption.toRequestBody() = when (this) {
    NetworkSearchVideoCaption.ANY -> "any"
    NetworkSearchVideoCaption.CLOSED_CAPTION -> "closedCaption"
    NetworkSearchVideoCaption.NONE -> "none"
}