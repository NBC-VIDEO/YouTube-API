package com.nbc.video.presenters.my.model

import java.time.LocalDateTime

data class MyVideo(
    val id: String,
    val title: String,
    val description: String,
    val thumbnailUrl: String,
    val views: Long,
    val writtenName: String,
    val dateTime: LocalDateTime
) {
}