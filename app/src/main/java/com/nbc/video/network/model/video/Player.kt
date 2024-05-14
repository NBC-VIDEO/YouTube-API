package com.nbc.video.network.model.video

import com.google.gson.annotations.SerializedName

data class Player(
    @SerializedName("embedHtml") val embedHtml: String,
    @SerializedName("embedHeight") val embedHeight: Long,
    @SerializedName("embedWidth") val embedWidth: Long,
)