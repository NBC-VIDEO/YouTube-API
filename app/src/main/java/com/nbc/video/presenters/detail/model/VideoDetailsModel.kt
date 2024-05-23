package com.nbc.video.presenters.detail.model

data class VideoDetailsModel(
    val items: List<VideoDetails>,
)

data class VideoDetails(
    val id: String?,                                 // 동영상 고유 ID
    val snippet: VideoSnippetModel?,
    val statistics: StatisticsModel?,
    val channelSnippet: ChannelSnippetModel?,
)

data class VideoSnippetModel(
    val publishedAt: String?,                   // 동영상 게시 일자
    val channelId: String?,                     // 채널 ID
    val title: String?,                              // 동영상 제목
    val description: String?,                   // 동영상 설명
    val thumbnails: ThumbnailsModel?,   // 썸네일
    val channelTitle: String?,                  // 채널 제목
    val tags: List<String>?,                    // 동영상 태그 목록
    val isLiked: Boolean = false,            // 좋아요 표시
)

data class ThumbnailsModel(
    val default: ThumbnailModel?,            // 썸네일
)

data class ThumbnailModel(
    val url: String?,                                // 이미지 URL
    val width: Int,                                 // 이미지 너비
    val height: Int,                                // 이미지 높이
)

data class StatisticsModel(
    val viewCount: Int?,                     // 조회수
)

data class ChannelSnippetModel(
    val thumbnails: ThumbnailsModel?,        // 채널 썸네일
)