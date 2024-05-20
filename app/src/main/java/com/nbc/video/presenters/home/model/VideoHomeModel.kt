package com.nbc.video.presenters.home.model

data class VideoHomeModel(
    val popularVideo: List<PopularVideo>,
    val categoryVideo: List<CategoryVideo>,
    val channelVideo: List<ChannelVideo>,
    val categoryList: List<Category> //카테고리 목록 리스트
)

//예시 - ("동물","카테고리아이디a"),("식물","카테고리아이디b")
data class Category(
    val categoryName: String, //카테고리 이름있어야 하지 않나?
    val categoryId: String,
    val channelId: String   //snippet의 channelId
    //Q. 카테고리 고른 후 영상에 카테고리 아이디가 포함되어야하니까 ?
    //Q. 카테고리 리스트에 채널 아이디 필요없지 않나?
    //Q. categoryId가 근데 카테고리 이름이랑 같은 건가??
)

//인기 있는 비디오 리스트
data class PopularVideo(
    val thumbnails: Thumbnail,
    val title: String
)


//카테고리 선택후 비디오 리스트
data class CategoryVideo(
    val thumbnails: Thumbnail,
    val title: String,
    val categoryId: String //카테고리 아이디
)

//카테고리 선택 후 나온 채널 리스트
data class ChannelVideo(
    val thumbnails: Thumbnail, //썸네일이 동영상 썸네일이 아닌 채널 프로필 사진....
    val title: String,  // 채널의 이름
    val channelId: String
    //카테고리 아이디 가져야하지 않나? 카테고리 아이디를 검색했을 때 해당하는 채널이 나오는거 아닌가?
    //채널 아이디
)

data class Thumbnail(
    val default: Image,
    val medium: Image,
    val height: Image,
)

data class Image(
    val url: String,
    val width: Int,
    val height: Int
)
