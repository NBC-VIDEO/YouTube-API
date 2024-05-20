package com.nbc.video.network.model.search.enums

enum class NetworkSearchVideoEmbeddable {
    ANY, TRUE
}

fun NetworkSearchVideoEmbeddable.toRequestBody() = when (this) {
    NetworkSearchVideoEmbeddable.ANY -> "any"
    NetworkSearchVideoEmbeddable.TRUE -> "true"
}