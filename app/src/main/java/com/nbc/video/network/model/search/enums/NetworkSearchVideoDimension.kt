package com.nbc.video.network.model.search.enums

enum class NetworkSearchVideoDimension {
    `2D`, `3D`, ANY
}

fun NetworkSearchVideoDimension.toRequestBody() = when (this) {
    NetworkSearchVideoDimension.`2D` -> "2D"
    NetworkSearchVideoDimension.`3D` -> "3D"
    NetworkSearchVideoDimension.ANY -> "ANY"

}