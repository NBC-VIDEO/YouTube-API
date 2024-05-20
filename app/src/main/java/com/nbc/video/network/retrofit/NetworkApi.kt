package com.nbc.video.network.retrofit

import androidx.annotation.IntRange
import com.nbc.video.network.YOUTUBE_API_KEY
import com.nbc.video.network.model.ChannelResponse
import com.nbc.video.network.model.SearchResponse
import com.nbc.video.network.model.VideoCategoriesResponse
import com.nbc.video.network.model.VideoResponse
import com.nbc.video.network.model.YouTubeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApi {

    // https://developers.google.com/youtube/v3/docs/videos/list?hl=ko&apix_params=%7B%22part%22%3A%5B%22snippet%22%5D%2C%22chart%22%3A%22mostPopular%22%7D#request

    @GET("/youtube/v3/videos")
    suspend fun getVideos(
        @Query("part") part: String,
        @Query("chart") chart: String?,
        @Query("id") id: String?,
        @Query("myRating") myRating: String? = null,
        @Query("hl") hl: String? = null,
        @Query("maxHeight") @IntRange(72, 8192) maxHeight: Int? = null,
        @Query("maxResults") @IntRange(0, 50) maxResults: Int = 5,
        @Query("maxWidth") @IntRange(72, 8192) maxWidth: Int? = null,
        @Query("onBehalfOfContentOwner") onBehalfOfContentOwner: String? = null,
        @Query("pageToken") pageToken: String? = null,
        @Query("regionCode") regionCode: String? = null,
        @Query("videoCategoryId") videoCategoryId: String? = null,
        @Query("key") key: String = YOUTUBE_API_KEY,
    ): YouTubeResponse<VideoResponse>

    // https://developers.google.com/youtube/v3/docs/search/list?hl=ko#http-request
    @GET("/youtube/v3/search")
    suspend fun search(
        @Query("part") part: String,
        @Query("channelId") channelId: String? = null,
        @Query("channelType") channelType: String? = null,
        @Query("eventType") eventType: String? = null,
        @Query("location") location: String? = null,
        @Query("locationRadius") locationRadius: String? = null,
        @Query("maxResults") @IntRange(0, 50) maxResults: Int = 5,
        @Query("onBehalfOfContentOwner") onBehalfOfContentOwner: String? = null,
        @Query("order") order: String? = null,
        @Query("pageToken") pageToken: String? = null,
        @Query("publishedAfter") publishedAfter: String? = null,
        @Query("publishedBefore") publishedBefore: String? = null,
        @Query("q") query: String? = null,
        @Query("regionCode") regionCode: String? = null,
        @Query("relevanceLanguage") relevanceLanguage: String? = null,
        @Query("safeSearch") safeSearch: String? = null,
        @Query("topicId") topicId: String? = null,
        @Query("type") type: String? = null,
        @Query("videoCaption") videoCaption: String? = null,
        @Query("videoCategoryId") videoCategoryId: String? = null,
        @Query("videoDefinition") videoDefinition: String? = null,
        @Query("videoDimension") videoDimension: String? = null,
        @Query("videoDuration") videoDuration: String? = null,
        @Query("videoEmbeddable") videoEmbeddable: String? = null,
        @Query("videoLicense") videoLicense: String? = null,
        @Query("videoPaidProductPlacement") videoPaidProductPlacement: String? = null,
        @Query("videoSyndicated") videoSyndicated: String? = null,
        @Query("videoType") videoType: String? = null,
        @Query("key") key: String = YOUTUBE_API_KEY,
    ): YouTubeResponse<SearchResponse>


    // https://developers.google.com/youtube/v3/docs/videoCategories/list?hl=ko#request

    @GET("/youtube/v3/videoCategories")
    suspend fun getVideoCategories(
        @Query("part") part: String = "snippet",
        @Query("id") id: String? = null,
        @Query("regionCode") regionCode: String? = null, // https://www.iso.org/iso-3166-country-codes.html,
        @Query("hl") hl: String? = null,
        @Query("key") key: String = YOUTUBE_API_KEY,
    ): YouTubeResponse<VideoCategoriesResponse>


    // https://developers.google.com/youtube/v3/docs/channels/list?hl=ko#request
    @GET("/youtube/v3/channels")
    suspend fun getChannels(
        @Query("part") part: String = "snippet",
        @Query("forHandle") forHandle: String? = null,
        @Query("forUsername") forUsername: String? = null,
        @Query("id") id: String? = null,
        @Query("managedByMe") managedByMe: Boolean? = null,
        @Query("mine") mine: Boolean? = null,
        @Query("hl") hl: String? = null,
        @Query("maxResults") @IntRange(0, 50) maxResults: Int = 5,
        @Query("onBehalfOfContentOwner") onBehalfOfContentOwner: String? = null,
        @Query("pageToken") pageToken: String? = null,
        @Query("key") key: String = YOUTUBE_API_KEY,
    ): YouTubeResponse<ChannelResponse>
}