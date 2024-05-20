package com.nbc.video.network.model.video

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Snippet(
    @SerializedName("publishedAt") val publishedAt: Date,
    @SerializedName("channelId") val channelId: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("thumbnails") val thumbnails: Map<String, com.nbc.video.network.model.video.Thumbnail>,
    @SerializedName("channelTitle") val channelTitle: String,
    @SerializedName("tags") val tags: List<String>,
    @SerializedName("categoryId") val categoryId: String,
    @SerializedName("liveBroadcastContent") val liveBroadcastContent: String,
    @SerializedName("defaultLanguage") val defaultLanguage: String,
    @SerializedName("localized") val localized: com.nbc.video.network.model.video.Localized,
    @SerializedName("defaultAudioLanguage") val defaultAudioLanguage: String,
)