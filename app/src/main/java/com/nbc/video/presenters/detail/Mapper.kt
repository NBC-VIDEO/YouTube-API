package com.nbc.video.presenters.detail

import com.nbc.video.database.model.VideoDetailEntity
import com.nbc.video.network.model.ChannelResponse
import com.nbc.video.network.model.VideoResponse
import com.nbc.video.network.model.YouTubeResponse
import com.nbc.video.presenters.detail.model.ChannelSnippetModel
import com.nbc.video.presenters.detail.model.StatisticsModel
import com.nbc.video.presenters.detail.model.ThumbnailModel
import com.nbc.video.presenters.detail.model.ThumbnailsModel
import com.nbc.video.presenters.detail.model.VideoDetails
import com.nbc.video.presenters.detail.model.VideoDetailsModel
import com.nbc.video.presenters.detail.model.VideoSnippetModel


internal fun YouTubeResponse<VideoResponse>.asExterminalModel(channelResponse: ChannelResponse): VideoDetailsModel {
    return VideoDetailsModel(
        items = this.items.map { items ->
            VideoDetails(
                id = items.id,
                snippet = VideoSnippetModel(
                    publishedAt = items.snippet.publishedAt,
                    channelId = items.snippet.channelId,
                    title = items.snippet.title,
                    description = items.snippet.description,
                    thumbnails = ThumbnailsModel(
                        default = ThumbnailModel(
                            url = items.snippet.thumbnails["high"]?.url,
                            width = 100,
                            height = 100,
                        )
                    ),
                    channelTitle = items.snippet.channelTitle,
                    tags = items.snippet.tags,
                    isLiked = false,
                ),
                statistics = StatisticsModel(
                    viewCount = items.statistics.viewCount.toInt(),
                ),
                channelSnippet = ChannelSnippetModel(
                    thumbnails = ThumbnailsModel(
                        default = ThumbnailModel(
                            url = channelResponse.snippet.thumbnails["high"]?.url,
                            width = 100,
                            height = 100,
                        )
                    )
                )
            )
        }
    )
}

internal fun VideoDetails.toEntity(isLike: Boolean) =
    VideoDetailEntity(
        id = id!!,
        channelId = snippet?.channelId!!,
        isLiked = isLike,
        title = snippet.title!!,
        description = snippet.description!!,
        thumbnailUrl = snippet.thumbnails!!.default!!.url!!,
        views = statistics!!.viewCount!!.toLong(),
        writtenName = snippet.channelTitle!!,
        dateTime = snippet.publishedAt!!
    )