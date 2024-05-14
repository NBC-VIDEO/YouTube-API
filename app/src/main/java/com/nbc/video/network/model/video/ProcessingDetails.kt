package com.nbc.video.network.model.video

import com.google.gson.annotations.SerializedName

data class ProcessingDetails(
    @SerializedName("processingStatus") val processingStatus: String,
    @SerializedName("processingProgress") val processingProgress: com.nbc.video.network.model.video.ProcessingProgress,
    @SerializedName("processingFailureReason") val processingFailureReason: String,
    @SerializedName("fileDetailsAvailability") val fileDetailsAvailability: String,
    @SerializedName("processingIssuesAvailability") val processingIssuesAvailability: String,
    @SerializedName("tagSuggestionsAvailability") val tagSuggestionsAvailability: String,
    @SerializedName("editorSuggestionsAvailability") val editorSuggestionsAvailability: String,
    @SerializedName("thumbnailsAvailability") val thumbnailsAvailability: String,
)
