package com.nbc.video.network.model.video

import com.google.gson.annotations.SerializedName

data class FileDetails(
    @SerializedName("fileName") val fileName: String,
    @SerializedName("fileSize") val fileSize: Long,
    @SerializedName("fileType") val fileType: String,
    @SerializedName("container") val container: String,
    @SerializedName("videoStreams") val videoStreams: List<com.nbc.video.network.model.video.VideoStream>,
    @SerializedName("audioStreams") val audioStreams: List<com.nbc.video.network.model.video.AudioStream>,
    @SerializedName("durationMs") val durationMs: Long,
    @SerializedName("bitrateBps") val bitrateBps: Long,
    @SerializedName("creationTime") val creationTime: String,
)