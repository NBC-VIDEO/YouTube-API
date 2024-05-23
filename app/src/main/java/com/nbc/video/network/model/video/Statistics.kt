package com.nbc.video.network.model.video

import com.google.gson.annotations.SerializedName

data class Statistics(
    @SerializedName("viewCount") val viewCount: String,
    @SerializedName("likeCount") val likeCount: String,
    @SerializedName("dislikeCount") val dislikeCount: String,
    @SerializedName("favoriteCount") val favoriteCount: String,
    @SerializedName("commentCount") val commentCount: String,
)