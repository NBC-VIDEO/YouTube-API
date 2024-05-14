package com.nbc.video.network.model.video

import com.google.gson.annotations.SerializedName

data class ContentDetails(
    @SerializedName("duration") val duration: String,
    @SerializedName("dimension") val dimension: String,
    @SerializedName("definition") val definition: String,
    @SerializedName("caption") val caption: String,
    @SerializedName("licensedContent") val licensedContent: Boolean,
    @SerializedName("regionRestriction") val regionRestriction: com.nbc.video.network.model.video.RegionRestriction,
    @SerializedName("contentRating") val contentRating: com.nbc.video.network.model.video.ContentRating,
    @SerializedName("projection") val projection: String,
    @SerializedName("hasCustomThumbnail") val hasCustomThumbnail: Boolean,
)