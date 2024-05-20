package com.nbc.video.network.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("kind") val kind: String,
    @SerializedName("etag") val eTag: String,
    @SerializedName("id") val id: Id,
    @SerializedName("snippet") val snippet: Snippet,
) {

    data class Id(
        @SerializedName("kind") val kind: String,
        @SerializedName("videoId") val videoId: String?,
        @SerializedName("channelId") val channelId: String?,
        @SerializedName("playlistId") val playlistId: String?,
    )

    data class Snippet(
        @SerializedName("publishedAt") val publishedAt: String,
        @SerializedName("channelId") val channelId: String,
        @SerializedName("title") val title: String,
        @SerializedName("description") val description: String,
        @SerializedName("thumbnails") val thumbnails: Map<String, Thumbnail>,
        @SerializedName("channelTitle") val channelTitle: String,
        @SerializedName("liveBroadcastContent") val liveBroadcastContent: String,
    ) {
        data class Thumbnail(
            @SerializedName("url") val url: String,
            @SerializedName("width") val width: Int,
            @SerializedName("height") val height: Int,
        )
    }
}