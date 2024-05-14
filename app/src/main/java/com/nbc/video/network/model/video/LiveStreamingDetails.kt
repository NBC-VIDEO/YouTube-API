package com.nbc.video.network.model.video

import com.google.gson.annotations.SerializedName
import java.util.Date

data class LiveStreamingDetails(
    @SerializedName("actualStartTime") val actualStartTime: Date,
    @SerializedName("actualEndTime") val actualEndTime: Date,
    @SerializedName("scheduledStartTime") val scheduledStartTime: Date,
    @SerializedName("scheduledEndTime") val scheduledEndTime: Date,
    @SerializedName("concurrentViewers") val concurrentViewers: Long,
    @SerializedName("activeLiveChatId") val activeLiveChatId: String,
)