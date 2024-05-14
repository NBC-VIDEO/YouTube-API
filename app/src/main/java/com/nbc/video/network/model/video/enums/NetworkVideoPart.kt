package com.nbc.video.network.model.video.enums

enum class NetworkVideoPart {
    CONTENT_DETAILS,
    FILE_DETAILS,
    ID,
    LIVE_STREAMING_DETAILS,
    LOCALIZATIONS,
    PLAYER,
    PROCESSING_DETAILS,
    RECORDING_DETAILS,
    SNIPPET,
    STATISTICS,
    STATUS,
    SUGGESTIONS,
    TOPIC_DETAILS
}

fun NetworkVideoPart.toRequestBody(): String = when (this) {
    NetworkVideoPart.CONTENT_DETAILS -> "contentDetails"
    NetworkVideoPart.FILE_DETAILS -> "fileDetails"
    NetworkVideoPart.ID -> "id"
    NetworkVideoPart.LIVE_STREAMING_DETAILS -> "liveStreamingDetails"
    NetworkVideoPart.LOCALIZATIONS -> "localizations"
    NetworkVideoPart.PLAYER -> "player"
    NetworkVideoPart.PROCESSING_DETAILS -> "processingDetails"
    NetworkVideoPart.RECORDING_DETAILS -> "recordingDetails"
    NetworkVideoPart.SNIPPET -> "snippet"
    NetworkVideoPart.STATISTICS -> "statistics"
    NetworkVideoPart.STATUS -> "status"
    NetworkVideoPart.SUGGESTIONS -> "suggestions"
    NetworkVideoPart.TOPIC_DETAILS -> "topicDetails"
}