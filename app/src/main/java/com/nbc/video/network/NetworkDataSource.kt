package com.nbc.video.network

import androidx.annotation.IntRange
import com.nbc.video.network.model.ChannelResponse
import com.nbc.video.network.model.SearchResponse
import com.nbc.video.network.model.VideoCategoriesResponse
import com.nbc.video.network.model.VideoResponse
import com.nbc.video.network.model.YouTubeResponse
import com.nbc.video.network.model.channel.enums.NetworkChannelPart
import com.nbc.video.network.model.search.enums.NetworkSearchChannelType
import com.nbc.video.network.model.search.enums.NetworkSearchEventType
import com.nbc.video.network.model.search.enums.NetworkSearchOrder
import com.nbc.video.network.model.search.enums.NetworkSearchType
import com.nbc.video.network.model.search.enums.NetworkSearchVideoCaption
import com.nbc.video.network.model.search.enums.NetworkSearchVideoDefinition
import com.nbc.video.network.model.search.enums.NetworkSearchVideoDimension
import com.nbc.video.network.model.search.enums.NetworkSearchVideoDuration
import com.nbc.video.network.model.search.enums.NetworkSearchVideoEmbeddable
import com.nbc.video.network.model.search.enums.NetworkSearchVideoLicense
import com.nbc.video.network.model.search.enums.NetworkSearchVideoPaidProductPlacement
import com.nbc.video.network.model.search.enums.NetworkSearchVideoSyndicated
import com.nbc.video.network.model.search.enums.NetworkSearchVideoType
import com.nbc.video.network.model.video.enums.NetworkVideoChart
import com.nbc.video.network.model.video.enums.NetworkVideoPart

interface NetworkDataSource :
    VideoRemoteDataSource,
    SearchRemoteDataSource,
    CategoriesRemoteDataSource,
    ChannelRemoteDataSource {

    override suspend fun getVideos(
        parts: List<NetworkVideoPart>,
        chart: NetworkVideoChart,
        hl: String?,
        @IntRange(72, 8192) maxHeight: Int?,
        @IntRange(0, 50) maxResults: Int,
        @IntRange(72, 8192) maxWidth: Int?,
        onBehalfOfContentOwner: String?,
        pageToken: String?,
        regionCode: String?,
        videoCategoryId: String?,
    ): YouTubeResponse<VideoResponse>

    override suspend fun getVideos(
        parts: List<NetworkVideoPart>,
        id: String,
        hl: String?,
        @IntRange(72, 8192) maxHeight: Int?,
        @IntRange(0, 50) maxResults: Int,
        @IntRange(72, 8192) maxWidth: Int?,
        onBehalfOfContentOwner: String?,
        pageToken: String?,
        regionCode: String?,
        videoCategoryId: String?,
    ): YouTubeResponse<VideoResponse>

    override suspend fun searchVideos(
        part: String,
        channelId: String?,
        channelType: NetworkSearchChannelType?,
        eventType: NetworkSearchEventType?,
        location: String?,
        locationRadius: String?,
        @IntRange(0, 50) maxResults: Int,
        onBehalfOfContentOwner: String?,
        order: NetworkSearchOrder?,
        pageToken: String?,
        publishedAfter: String?,
        publishedBefore: String?,
        query: String?,
        regionCode: String?,
        relevanceLanguage: String?,
        safeSearch: String?,
        topicId: String?,
        type: NetworkSearchType?,
        videoCaption: NetworkSearchVideoCaption?,
        videoCategoryId: String?,
        videoDefinition: NetworkSearchVideoDefinition?,
        videoDimension: NetworkSearchVideoDimension?,
        videoDuration: NetworkSearchVideoDuration?,
        videoEmbeddable: NetworkSearchVideoEmbeddable?,
        videoLicense: NetworkSearchVideoLicense?,
        videoPaidProductPlacement: NetworkSearchVideoPaidProductPlacement?,
        videoSyndicated: NetworkSearchVideoSyndicated?,
        videoType: NetworkSearchVideoType?,
    ): YouTubeResponse<SearchResponse>

    override suspend fun getVideoIdCategories(
        part: String,
        id: String?,
        hl: String?,
    ): YouTubeResponse<VideoCategoriesResponse>

    override suspend fun getVideoRegionCodeCategories(
        part: String,
        regionCode: String?,
        hl: String?,
    ): YouTubeResponse<VideoCategoriesResponse>

    override suspend fun getVideoHandleChannel(
        part: List<NetworkChannelPart>,
        forHandle: String,
        hl: String?,
        @IntRange(0, 50) maxResults: Int,
        onBehalfOfContentOwner: String?,
        pageToken: String?,
    ): YouTubeResponse<ChannelResponse>

    override suspend fun getVideoUserChannel(
        part: List<NetworkChannelPart>,
        forUsername: String,
        hl: String?,
        @IntRange(0, 50) maxResults: Int,
        onBehalfOfContentOwner: String?,
        pageToken: String?,
    ): YouTubeResponse<ChannelResponse>

    override suspend fun getVideoIdChannel(
        part: List<NetworkChannelPart>,
        id: String,
        hl: String?,
        @IntRange(0, 50) maxResults: Int,
        onBehalfOfContentOwner: String?,
        pageToken: String?,
    ): YouTubeResponse<ChannelResponse>
}