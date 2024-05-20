package com.nbc.video.network.model.video

import com.google.gson.annotations.SerializedName

data class Localization(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
)
