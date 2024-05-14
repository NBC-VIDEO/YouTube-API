package com.nbc.video.network.model.video

import com.google.gson.annotations.SerializedName

data class Thumbnail(
    @SerializedName("name") val url: String,
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int,
)