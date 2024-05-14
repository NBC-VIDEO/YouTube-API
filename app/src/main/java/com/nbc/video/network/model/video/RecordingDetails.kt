package com.nbc.video.network.model.video

import com.google.gson.annotations.SerializedName
import java.util.Date

data class RecordingDetails(
    @SerializedName("recordingDate") val recordingDate: Date,
)