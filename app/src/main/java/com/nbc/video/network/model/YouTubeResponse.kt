package com.nbc.video.network.model

import com.google.gson.annotations.SerializedName

data class YouTubeResponse<T>(
    @SerializedName("kind") val kind: String,
    @SerializedName("etag") val eTag: String,
    @SerializedName("nextPageToken") val nextPageToken: String,
    @SerializedName("prevPageToken") val prevPageToken: String,
    @SerializedName("pageInfo") val pageInfo: PageInfo,
    @SerializedName("items") val items: List<T>
) {
    data class PageInfo(
        @SerializedName("totalResults") val totalResults: Int,
        @SerializedName("resultsPerPage") val resultsPerPage: Int
    )
}