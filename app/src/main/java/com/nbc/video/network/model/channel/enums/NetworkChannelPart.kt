package com.nbc.video.network.model.channel.enums

enum class NetworkChannelPart {
    AUDIT_DETAILS,
    BRANDING_SETTINGS,
    CONTENT_DETAILS,
    CONTENT_OWNER_DETAILS,
    ID,
    LOCALIZATIONS,
    SNIPPET,
    STATISTICS,
    STATUS,
    TOPIC_DETAILS
}

fun NetworkChannelPart.toRequestBody(): String =
    when (this) {
        NetworkChannelPart.AUDIT_DETAILS -> "auditDetails"
        NetworkChannelPart.BRANDING_SETTINGS -> "brandingSettings"
        NetworkChannelPart.CONTENT_DETAILS -> "contentDetails"
        NetworkChannelPart.CONTENT_OWNER_DETAILS -> "contentOwnerDetails"
        NetworkChannelPart.ID -> "id"
        NetworkChannelPart.LOCALIZATIONS -> "localizations"
        NetworkChannelPart.SNIPPET -> "snippet"
        NetworkChannelPart.STATISTICS -> "statistics"
        NetworkChannelPart.STATUS -> "status"
        NetworkChannelPart.TOPIC_DETAILS -> "topicDetails"
    }