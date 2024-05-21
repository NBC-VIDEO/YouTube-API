package com.nbc.video.presenters.home.model

data class VideoHomeModel(
    val popularVideo: List<PopularVideo>,
    val categoryVideo: List<CategoryVideo>,
    val channelVideo: List<ChannelVideo>,
    val categoryList: List<Category> //카테고리 목록 리스트
)

data class Category(
    val categoryTitle: String, //카테고리 이름
    val categoryId: String,
    val channelId: String
)

//1. 인기 있는 비디오 리스트
data class PopularVideo(
    val id: String,
    val thumbnails: Thumbnail,
    val title: String
)


//2. 카테고리 선택후 비디오 리스트
data class CategoryVideo(
    val id: String,
    val thumbnails: Thumbnail,
    val title: String,
    val categoryId: String //카테고리 아이디
)

//3. 카테고리 선택 후 채널 리스트
data class ChannelVideo(
    val id: String,
    val thumbnails: Thumbnail,
    val title: String,  // 채널의 이름
    val channelId: String
)

data class Thumbnail(
    val default: Image,
    val medium: Image,
    val high: Image,
)

data class Image(
    val url: String,
    val width: Int,
    val height: Int
)
