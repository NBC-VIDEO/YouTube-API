package com.nbc.video.network.model.video

import com.google.gson.annotations.SerializedName

data class AudioStream(
    @SerializedName("channelCount") val channelCount: Int,
    @SerializedName("codec") val codec: String,
    @SerializedName("bitrateBps") val bitrateBps: Long,
    @SerializedName("vendor") val vendor: String,
)