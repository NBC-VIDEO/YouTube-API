package com.nbc.video.presenters.home

import com.nbc.video.network.model.SearchResponse
import com.nbc.video.network.model.VideoResponse
import com.nbc.video.presenters.home.model.CategoryVideo
import com.nbc.video.presenters.home.model.ChannelVideo
import com.nbc.video.presenters.home.model.Image
import com.nbc.video.presenters.home.model.PopularVideo
import com.nbc.video.presenters.home.model.Thumbnail

//1. Popular 리스트
fun VideoResponse.toPopular(): PopularVideo =
    PopularVideo(
        id = id,
        thumbnails = Thumbnail(
            default = Image(
                url = this.snippet.thumbnails["default"]?.url ?: "",
                width = this.snippet.thumbnails["default"]?.width ?: 0,
                height = this.snippet.thumbnails["default"]?.height ?: 0
            ),
            medium = Image(
                url = this.snippet.thumbnails["medium"]?.url ?: "",
                width = this.snippet.thumbnails["medium"]?.width ?: 0,
                height = this.snippet.thumbnails["medium"]?.height ?: 0
            ),
            high = Image(
                url = this.snippet.thumbnails["high"]?.url ?: "",
                width = this.snippet.thumbnails["high"]?.width ?: 0,
                height = this.snippet.thumbnails["high"]?.height ?: 0
            )
        ),
        title = this.snippet.title
    )


//2. Category 에 따른 영상 리스트
fun SearchResponse.toChannel(): ChannelVideo =
    ChannelVideo(
        id = id.videoId ?: "",
        thumbnails = Thumbnail(
            default = Image(
                url = this.snippet.thumbnails["default"]?.url ?: "",
                width = this.snippet.thumbnails["default"]?.width ?: 0,
                height = this.snippet.thumbnails["default"]?.height ?: 0
            ),
            medium = Image(
                url = this.snippet.thumbnails["medium"]?.url ?: "",
                width = this.snippet.thumbnails["medium"]?.width ?: 0,
                height = this.snippet.thumbnails["medium"]?.height ?: 0
            ),
            high = Image(
                url = this.snippet.thumbnails["high"]?.url ?: "",
                width = this.snippet.thumbnails["high"]?.width ?: 0,
                height = this.snippet.thumbnails["high"]?.height ?: 0
            )
        ),
        title = this.snippet.title, channelId = this.snippet.channelId
    )


//3. Category 에 따른 Channel 리스트
fun VideoResponse.toCategory(): CategoryVideo =
    CategoryVideo(
        id = id,
        thumbnails = Thumbnail(
            default = Image(
                url = this.snippet.thumbnails["default"]?.url ?: "", //null인 경우 ""
                width = this.snippet.thumbnails["default"]?.width ?: 0,
                height = this.snippet.thumbnails["default"]?.height ?: 0

            ),
            medium = Image(
                url = this.snippet.thumbnails["medium"]?.url ?: "",
                width = this.snippet.thumbnails["medium"]?.width ?: 0,
                height = this.snippet.thumbnails["medium"]?.height ?: 0
            ),
            high = Image(
                url = this.snippet.thumbnails["high"]?.url ?: "",
                width = this.snippet.thumbnails["high"]?.width ?: 0,
                height = this.snippet.thumbnails["high"]?.height ?: 0
            )
        ),
        title = this.snippet.title, categoryId = this.snippet.categoryId //카테고리 아이디
    )
