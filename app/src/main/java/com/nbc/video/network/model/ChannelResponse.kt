package com.nbc.video.network.model

import com.google.gson.annotations.SerializedName

data class ChannelResponse(
    @SerializedName("snippet")
    val snippet: Snippet,
    @SerializedName("contentDetails")
    val contentDetails: ContentDetails,
    @SerializedName("statistics")
    val statistics: Statistics,
    @SerializedName("topicDetails")
    val topicDetails: TopicDetails,
    @SerializedName("status")
    val status: Status,
    @SerializedName("brandingSettings")
    val brandingSettings: BrandingSettings,
    @SerializedName("auditDetails")
    val auditDetails: AuditDetails,
    @SerializedName("contentOwnerDetails")
    val contentOwnerDetails: ContentOwnerDetails,
    @SerializedName("localizations")
    val localizations: Map<String, Localization>,
) {
    data class Snippet(
        @SerializedName("title")
        val title: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("customUrl")
        val customUrl: String,
        @SerializedName("publishedAt")
        val publishedAt: String,
        @SerializedName("thumbnails")
        val thumbnails: Map<String, Thumbnail>,
        @SerializedName("defaultLanguage")
        val defaultLanguage: String,
        @SerializedName("localized")
        val localized: Localized,
        @SerializedName("country")
        val country: String,
    )

    data class Thumbnail(
        @SerializedName("url")
        val url: String,
        @SerializedName("width")
        val width: Int,
        @SerializedName("height")
        val height: Int,
    )

    data class Localized(
        @SerializedName("title")
        val title: String,
        @SerializedName("description")
        val description: String,
    )

    data class ContentDetails(
        @SerializedName("relatedPlaylists")
        val relatedPlaylists: RelatedPlaylists,
    )

    data class RelatedPlaylists(
        @SerializedName("likes")
        val likes: String,
        @SerializedName("favorites")
        val favorites: String,
        @SerializedName("uploads")
        val uploads: String,
    )

    data class Statistics(
        @SerializedName("viewCount")
        val viewCount: Long,
        @SerializedName("subscriberCount")
        val subscriberCount: Long,
        @SerializedName("hiddenSubscriberCount")
        val hiddenSubscriberCount: Boolean,
        @SerializedName("videoCount")
        val videoCount: Long,
    )

    data class TopicDetails(
        @SerializedName("topicIds")
        val topicIds: List<String>,
        @SerializedName("topicCategories")
        val topicCategories: List<String>,
    )

    data class Status(
        @SerializedName("privacyStatus")
        val privacyStatus: String,
        @SerializedName("isLinked")
        val isLinked: Boolean,
        @SerializedName("longUploadsStatus")
        val longUploadsStatus: String,
        @SerializedName("madeForKids")
        val madeForKids: Boolean,
        @SerializedName("selfDeclaredMadeForKids")
        val selfDeclaredMadeForKids: Boolean,
    )

    data class BrandingSettings(
        @SerializedName("channel")
        val channel: Channel,
        @SerializedName("watch")
        val watch: Watch,
    ) {
        data class Channel(
            @SerializedName("title")
            val title: String,
            @SerializedName("description")
            val description: String,
            @SerializedName("keywords")
            val keywords: String,
            @SerializedName("trackingAnalyticsAccountId")
            val trackingAnalyticsAccountId: String,
            @SerializedName("unsubscribedTrailer")
            val unsubscribedTrailer: String,
            @SerializedName("defaultLanguage")
            val defaultLanguage: String,
            @SerializedName("country")
            val country: String,
        )

        data class Watch(
            @SerializedName("textColor")
            val textColor: String,
            @SerializedName("backgroundColor")
            val backgroundColor: String,
            @SerializedName("featuredPlaylistId")
            val featuredPlaylistId: String,
        )
    }

    data class AuditDetails(
        @SerializedName("overallGoodStanding")
        val overallGoodStanding: Boolean,
        @SerializedName("communityGuidelinesGoodStanding")
        val communityGuidelinesGoodStanding: Boolean,
        @SerializedName("copyrightStrikesGoodStanding")
        val copyrightStrikesGoodStanding: Boolean,
        @SerializedName("contentIdClaimsGoodStanding")
        val contentIdClaimsGoodStanding: Boolean,
    )

    data class ContentOwnerDetails(
        @SerializedName("contentOwner")
        val contentOwner: String,
        @SerializedName("timeLinked")
        val timeLinked: String,
    )

    data class Localization(
        @SerializedName("title")
        val title: String,
        @SerializedName("description")
        val description: String,
    )
}
