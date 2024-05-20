package com.nbc.video.network.model.video

import com.google.gson.annotations.SerializedName

data class TopicDetails(
    @SerializedName("topicIds") val topicIds: List<String>,
    @SerializedName("relevantTopicIds") val relevantTopicIds: List<String>,
    @SerializedName("topicCategories") val topicCategories: List<String>,
)