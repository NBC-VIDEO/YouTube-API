package com.nbc.video.network.model.video

import com.google.gson.annotations.SerializedName

data class VideoStream(
    @SerializedName("widthPixels") val widthPixels: Int,
    @SerializedName("heightPixels") val heightPixels: Int,
    @SerializedName("frameRateFps") val frameRateFps: Double,
    @SerializedName("aspectRatio") val aspectRatio: Double,
    @SerializedName("codec") val codec: String,
    @SerializedName("bitrateBps") val bitrateBps: Long,
    @SerializedName("rotation") val rotation: String,
    @SerializedName("vendor") val vendor: String,
)