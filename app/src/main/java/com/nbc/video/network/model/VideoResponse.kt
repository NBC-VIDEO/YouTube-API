package com.nbc.video.network.model

import com.google.gson.annotations.SerializedName

data class VideoResponse(
    @SerializedName("kind") val kind: String,
    @SerializedName("etag") val eTag: String,
    @SerializedName("id") val id: String,
    @SerializedName("snippet") val snippet: com.nbc.video.network.model.video.Snippet,
    @SerializedName("contentDetails") val contentDetails: com.nbc.video.network.model.video.ContentDetails,
    @SerializedName("status") val status: com.nbc.video.network.model.video.Status,
    @SerializedName("statistics") val statistics: com.nbc.video.network.model.video.Statistics,
    @SerializedName("player") val player: com.nbc.video.network.model.video.Player,
    @SerializedName("topicDetails") val topicDetails: com.nbc.video.network.model.video.TopicDetails,
    @SerializedName("recordingDetails") val recordingDetails: com.nbc.video.network.model.video.RecordingDetails,
    @SerializedName("fileDetails") val fileDetails: com.nbc.video.network.model.video.FileDetails,
    @SerializedName("processingDetails") val processingDetails: com.nbc.video.network.model.video.ProcessingDetails,
    @SerializedName("suggestions") val suggestions: com.nbc.video.network.model.video.Suggestions,
    @SerializedName("liveStreamingDetails") val liveStreamingDetails: com.nbc.video.network.model.video.LiveStreamingDetails,
    @SerializedName("localizations") val localizations: Map<String, com.nbc.video.network.model.video.Localization>,
)

