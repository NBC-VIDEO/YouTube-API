package com.nbc.video.presenters.my_video.model

import java.time.LocalDateTime

data class MyVideo(
    val id: String,
    val title: String,
    val description: String,
    val thumbnail: Thumbnail,
    val views: Long,
    val writtenName: String,
    val dateTime: LocalDateTime,
) {

    data class Thumbnail(
        val default: Image,
        val medium: Image,
        val high: Image,
    )

    data class Image(
        val url: String,
        val width: Int,
        val high: Int,
    )
}