package com.nbc.video.network.model.video

import com.google.gson.annotations.SerializedName

data class Suggestions(
    @SerializedName("processingErrors") val processingErrors: List<String>,
    @SerializedName("processingWarnings") val processingWarnings: List<String>,
    @SerializedName("processingHints") val processingHints: List<String>,
    @SerializedName("tagSuggestions") val tagSuggestions: List<com.nbc.video.network.model.video.TagSuggestion>,
    @SerializedName("editorSuggestions") val editorSuggestions: List<String>,
)