package com.nbc.video.presenters.detail.data

import com.nbc.video.presenters.detail.model.ChannelSnippetModel
import com.nbc.video.presenters.detail.model.StatisticsModel
import com.nbc.video.presenters.detail.model.ThumbnailModel
import com.nbc.video.presenters.detail.model.ThumbnailsModel
import com.nbc.video.presenters.detail.model.VideoDetails
import com.nbc.video.presenters.detail.model.VideoDetailsModel
import com.nbc.video.presenters.detail.model.VideoSnippetModel

object DetailDummyData {
    private var _user : VideoDetailsModel

    val user : VideoDetailsModel get() = _user

    init {
        _user = createDummyData()
    }

    private fun createDummyData() : VideoDetailsModel {
        val thumbnail = ThumbnailModel(
            url = "https://cdn-icons-png.flaticon.com/512/3214/3214746.png",
            width = 100,
            height = 100,
        )
        val userSnippet = VideoSnippetModel(
            publishedAt = "Jul 1, 2021",
            channelId = "User-123123123123",
            title = "6키로가 빠지는 다이어트 잡채밥 고양이 합방 먹방",
            description = "잘 먹었습니다 :)",
            thumbnails = ThumbnailsModel(thumbnail),
            channelTitle = "박병진용사",
            tags = listOf("All", "Music"),
            categoryId = 1,
        )
        val channelSnippet = ChannelSnippetModel(
            thumbnails = ThumbnailsModel(thumbnail)
        )
        val userStatistics = StatisticsModel(60883)
        val videoDetails = VideoDetails(
            id = "9bZkp7q19f0",
            snippet = userSnippet,
            statistics = userStatistics,
            channelSnippet = channelSnippet,
        )
        val user = VideoDetailsModel(
            items = videoDetails
        )
        return user
    }
}