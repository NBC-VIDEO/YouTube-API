package com.nbc.video.network.model.video

import com.google.gson.annotations.SerializedName

data class ProcessingProgress(
    @SerializedName("partsTotal") val partsTotal: Long,
    @SerializedName("partsProcessed") val partsProcessed: Long,
    @SerializedName("timeLeftMs") val timeLeftMs: Long,
)