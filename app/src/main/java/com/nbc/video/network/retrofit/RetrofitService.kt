package com.nbc.video.network.retrofit

import com.nbc.video.network.BASE_URL
import com.nbc.video.network.NetworkDataSource
import com.nbc.video.network.model.ChannelResponse
import com.nbc.video.network.model.SearchResponse
import com.nbc.video.network.model.VideoCategoriesResponse
import com.nbc.video.network.model.VideoResponse
import com.nbc.video.network.model.YouTubeResponse
import com.nbc.video.network.model.channel.enums.NetworkChannelPart
import com.nbc.video.network.model.channel.enums.toRequestBody
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
import com.nbc.video.network.model.search.enums.toRequestBody
import com.nbc.video.network.model.video.enums.NetworkVideoChart
import com.nbc.video.network.model.video.enums.NetworkVideoPart
import com.nbc.video.network.model.video.enums.toRequestBody
import okhttp3.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

internal class RetrofitService(
    callFactory: Call.Factory,
) : NetworkDataSource {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .callFactory(callFactory)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val networkApi: NetworkApi = retrofit.create()

    override suspend fun getVideos(
        parts: List<NetworkVideoPart>,
        chart: NetworkVideoChart,
        hl: String?,
        maxHeight: Int?,
        maxResults: Int,
        maxWidth: Int?,
        onBehalfOfContentOwner: String?,
        pageToken: String?,
        regionCode: String?,
        videoCategoryId: String?,
    ): YouTubeResponse<VideoResponse> {
        return networkApi.getVideos(
            part = parts
                .map { it.toRequestBody() }
                .joinToString(""),
            chart = chart.toRequestBody(),
            id = null,
            myRating = null,
            hl = hl,
            maxHeight = maxHeight,
            maxResults = maxResults,
            maxWidth = maxWidth,
            onBehalfOfContentOwner = onBehalfOfContentOwner,
            pageToken = pageToken,
            regionCode = regionCode,
            videoCategoryId = videoCategoryId
        )
    }

    override suspend fun getVideos(
        parts: List<NetworkVideoPart>,
        id: String,
        hl: String?,
        maxHeight: Int?,
        maxResults: Int,
        maxWidth: Int?,
        onBehalfOfContentOwner: String?,
        pageToken: String?,
        regionCode: String?,
        videoCategoryId: String?,
    ): YouTubeResponse<VideoResponse> {
        return networkApi.getVideos(
            part = parts.map { it.toRequestBody() }.joinToString(", "),
            chart = null,
            id = id,
            myRating = null,
            hl = hl,
            maxHeight = maxHeight,
            maxResults = maxResults,
            maxWidth = maxWidth,
            onBehalfOfContentOwner = onBehalfOfContentOwner,
            pageToken = pageToken,
            regionCode = regionCode,
            videoCategoryId = videoCategoryId
        )
    }

    override suspend fun searchVideos(
        part: String,
        channelId: String?,
        channelType: NetworkSearchChannelType?,
        eventType: NetworkSearchEventType?,
        location: String?,
        locationRadius: String?,
        maxResults: Int,
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
    ): YouTubeResponse<SearchResponse> {
        return networkApi.search(
            part = part,
            channelId = channelId,
            channelType = channelType?.toRequestBody(),
            eventType = eventType?.toRequestBody(),
            location = location,
            locationRadius = locationRadius,
            maxResults = maxResults,
            onBehalfOfContentOwner = onBehalfOfContentOwner,
            order = order?.toRequestBody(),
            pageToken = pageToken,
            publishedAfter = publishedAfter,
            publishedBefore = publishedBefore,
            query = query,
            regionCode = regionCode,
            relevanceLanguage = relevanceLanguage,
            safeSearch = safeSearch,
            topicId = topicId,
            type = type?.toRequestBody(),
            videoCaption = videoCategoryId,
            videoCategoryId = videoCaption?.toRequestBody(),
            videoDefinition = videoDefinition?.toRequestBody(),
            videoDimension = videoDimension?.toRequestBody(),
            videoDuration = videoDuration?.toRequestBody(),
            videoEmbeddable = videoEmbeddable?.toRequestBody(),
        )
    }

    override suspend fun getVideoIdCategories(
        part: String,
        id: String?,
        hl: String?,
    ): YouTubeResponse<VideoCategoriesResponse> {
        return networkApi.getVideoCategories(
            part = part, id = id, hl = hl
        )
    }

    override suspend fun getVideoRegionCodeCategories(
        part: String,
        regionCode: String?,
        hl: String?,
    ): YouTubeResponse<VideoCategoriesResponse> {
        return networkApi.getVideoCategories(
            part = part, regionCode = regionCode, hl = hl
        )
    }

    override suspend fun getVideoHandleChannel(
        part: List<NetworkChannelPart>,
        forHandle: String,
        hl: String?,
        maxResults: Int,
        onBehalfOfContentOwner: String?,
        pageToken: String?,
    ): YouTubeResponse<ChannelResponse> {
        return networkApi.getChannels(
            part = part
                .map { it.toRequestBody() }
                .joinToString(", "),
            forHandle = forHandle,
            hl = hl,
            maxResults = maxResults,
            onBehalfOfContentOwner = onBehalfOfContentOwner,
            pageToken = pageToken
        )
    }

    override suspend fun getVideoUserChannel(
        part: List<NetworkChannelPart>,
        forUsername: String,
        hl: String?,
        maxResults: Int,
        onBehalfOfContentOwner: String?,
        pageToken: String?,
    ): YouTubeResponse<ChannelResponse> {
        return networkApi.getChannels(
            part = part
                .map { it.toRequestBody() }
                .joinToString(", "),
            forUsername = forUsername,
            hl = hl,
            maxResults = maxResults,
            onBehalfOfContentOwner = onBehalfOfContentOwner,
            pageToken = pageToken
        )
    }

    override suspend fun getVideoIdChannel(
        part: List<NetworkChannelPart>,
        id: String,
        hl: String?,
        maxResults: Int,
        onBehalfOfContentOwner: String?,
        pageToken: String?,
    ): YouTubeResponse<ChannelResponse> {
        return networkApi.getChannels(
            part = part
                .map { it.toRequestBody() }
                .joinToString(", "),
            id = id,
            hl = hl,
            maxResults = maxResults,
            onBehalfOfContentOwner = onBehalfOfContentOwner,
            pageToken = pageToken
        )
    }
}
