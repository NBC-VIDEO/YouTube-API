package com.nbc.video.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "video_detail")
data class VideoDetailEntity(
    @PrimaryKey
    val id : String,
    val channelId : String,
    var isLiked : Boolean = false
)
