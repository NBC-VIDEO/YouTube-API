package com.nbc.video.network.model.search.enums

enum class NetworkSearchVideoDefinition {
    ANY, HIGH, STANDARD
}

fun NetworkSearchVideoDefinition.toRequestBody() = when (this) {
    NetworkSearchVideoDefinition.ANY -> "any"
    NetworkSearchVideoDefinition.HIGH -> "high"
    NetworkSearchVideoDefinition.STANDARD -> "standard"
}