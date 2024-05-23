package com.nbc.video.network.model

import com.google.gson.annotations.SerializedName

data class VideoCategoriesResponse(
    @SerializedName("kind") val kind: String,
    @SerializedName("etag") val eTag: String,
    @SerializedName("id") val id: String,
    @SerializedName("snippet") val snippet: Snippet,
) {
    data class Snippet(
        @SerializedName("channelId") val channelId: String,
        @SerializedName("title") val title: String,
        @SerializedName("assignable") val assignable: Boolean,
    )
}