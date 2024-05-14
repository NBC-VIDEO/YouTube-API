package com.nbc.video.network.model.video

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Status(
    @SerializedName("uploadStatus") val uploadStatus: String,
    @SerializedName("failureReason") val failureReason: String,
    @SerializedName("rejectionReason") val rejectionReason: String,
    @SerializedName("privacyStatus") val privacyStatus: String,
    @SerializedName("publishAt") val publishAt: Date,
    @SerializedName("license") val license: String,
    @SerializedName("embeddable") val embeddable: Boolean,
    @SerializedName("publicStatsViewable") val publicStatsViewable: Boolean,
    @SerializedName("madeForKids") val madeForKids: Boolean,
    @SerializedName("selfDeclaredMadeForKids") val selfDeclaredMadeForKids: Boolean,
)