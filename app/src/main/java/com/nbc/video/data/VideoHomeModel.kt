package com.nbc.video.data

data class VideoHomeModel(
    val popularVideo: List<PopularVideo>,
    val categoryVideo: List<CategoryVideo>,
    val channelVideo: List<ChannelVideo>
)

data class Category(
    val id: Int,  //카테고리 아이디
    val channelId: String
)

data class PopularVideo(
    val thumbnails: Thumbnail,
    val title: String,
    val id: Int
)

data class CategoryVideo(
    val thumbnails: Thumbnail,
    val title: String,
    val id: Int
)

data class ChannelVideo(
    val thumbnails: Thumbnail,
    val title: String,
    val id: Int
)

data class Thumbnail(
    val default: Image,
    val medium: Image,
    val heigh: Image,
)

data class Image(
    val url: String,
    val width: Int,
    val heigh: Int
)
