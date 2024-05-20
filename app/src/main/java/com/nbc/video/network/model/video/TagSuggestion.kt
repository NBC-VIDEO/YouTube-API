package com.nbc.video.network.model.video

import com.google.gson.annotations.SerializedName

data class TagSuggestion(
    @SerializedName("tag") val tag: String,
    @SerializedName("categoryRestricts") val categoryRestricts: List<String>,
)
