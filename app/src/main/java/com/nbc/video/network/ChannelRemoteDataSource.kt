package com.nbc.video.network

import androidx.annotation.IntRange
import com.nbc.video.network.model.ChannelResponse
import com.nbc.video.network.model.YouTubeResponse
import com.nbc.video.network.model.channel.enums.NetworkChannelPart

interface ChannelRemoteDataSource {
    /**
     * 매개변수 설명
     *
     * [part]: part 매개변수는 API 응답에 포함될 하나 이상의 channel 리소스 속성의 쉼표로 구분된 목록을 지정합니다.
     * 매개변수가 하위 속성을 포함하는 속성을 식별하는 경우 하위 속성이 응답에 포함됩니다.
     *
     * [forHandle]: forHandle 매개변수는 YouTube 핸들을 지정하여 이 핸들과 연결된 채널을 요청합니다.
     *
     * [hl]: hl 매개변수는 YouTube 웹사이트에서 지원하는 특정 애플리케이션 언어에 대해 현지화된 리소스 메타데이터를 검색하도록 API에 지시합니다.
     *
     * [maxResults]: maxResults 매개변수는 결과 집합에 반환해야 하는 최대 항목 수를 지정합니다.
     *
     * [onBehalfOfContentOwner]: 이 매개변수는 제대로 승인된 요청에서만 사용할 수 있습니다. onBehalfOfContentOwner 매개변수는 요청의 승인 사용자 인증 정보가 매개변수 값에 지정된 콘텐츠 소유자를 대신하는 YouTube CMS 사용자를 식별함을 나타냅니다.
     *
     * [pageToken]: pageToken 매개변수는 반환해야 하는 결과 집합의 특정 페이지를 식별합니다.
     */

    suspend fun getVideoHandleChannel(
        part: List<NetworkChannelPart> = listOf(NetworkChannelPart.SNIPPET),
        forHandle: String,
        hl: String? = null,
        @IntRange(0, 50) maxResults: Int = 5,
        onBehalfOfContentOwner: String? = null,
        pageToken: String? = null,
    ): YouTubeResponse<ChannelResponse>

    /**
     * 매개변수 설명
     *
     * [part]: part 매개변수는 API 응답에 포함될 하나 이상의 channel 리소스 속성의 쉼표로 구분된 목록을 지정합니다.
     * 매개변수가 하위 속성을 포함하는 속성을 식별하는 경우 하위 속성이 응답에 포함됩니다.
     *
     * [forUsername]: forUsername 매개변수는 YouTube 사용자 이름을 지정하여 사용자 이름과 연결된 채널을 요청합니다.
     *
     * [hl]: hl 매개변수는 YouTube 웹사이트에서 지원하는 특정 애플리케이션 언어에 대해 현지화된 리소스 메타데이터를 검색하도록 API에 지시합니다.
     *
     * [maxResults]: maxResults 매개변수는 결과 집합에 반환해야 하는 최대 항목 수를 지정합니다.
     *
     * [onBehalfOfContentOwner]: 이 매개변수는 제대로 승인된 요청에서만 사용할 수 있습니다. onBehalfOfContentOwner 매개변수는 요청의 승인 사용자 인증 정보가 매개변수 값에 지정된 콘텐츠 소유자를 대신하는 YouTube CMS 사용자를 식별함을 나타냅니다.
     *
     * [pageToken]: pageToken 매개변수는 반환해야 하는 결과 집합의 특정 페이지를 식별합니다.
     */

    suspend fun getVideoUserChannel(
        part: List<NetworkChannelPart> = listOf(NetworkChannelPart.SNIPPET),
        forUsername: String,
        hl: String? = null,
        @IntRange(0, 50) maxResults: Int = 5,
        onBehalfOfContentOwner: String? = null,
        pageToken: String? = null,
    ): YouTubeResponse<ChannelResponse>

    /**
     * 매개변수 설명
     *
     * [part]: part 매개변수는 API 응답에 포함될 하나 이상의 channel 리소스 속성의 쉼표로 구분된 목록을 지정합니다.
     * 매개변수가 하위 속성을 포함하는 속성을 식별하는 경우 하위 속성이 응답에 포함됩니다.
     *
     * [id]: id 매개변수는 검색되는 리소스에 대한 YouTube 채널 ID의 쉼표로 구분된 목록을 지정합니다.
     *
     * [hl]: hl 매개변수는 YouTube 웹사이트에서 지원하는 특정 애플리케이션 언어에 대해 현지화된 리소스 메타데이터를 검색하도록 API에 지시합니다.
     *
     * [maxResults]: maxResults 매개변수는 결과 집합에 반환해야 하는 최대 항목 수를 지정합니다.
     *
     * [onBehalfOfContentOwner]: 이 매개변수는 제대로 승인된 요청에서만 사용할 수 있습니다. onBehalfOfContentOwner 매개변수는 요청의 승인 사용자 인증 정보가 매개변수 값에 지정된 콘텐츠 소유자를 대신하는 YouTube CMS 사용자를 식별함을 나타냅니다.
     *
     * [pageToken]: pageToken 매개변수는 반환해야 하는 결과 집합의 특정 페이지를 식별합니다.
     */

    suspend fun getVideoIdChannel(
        part: List<NetworkChannelPart> = listOf(NetworkChannelPart.SNIPPET),
        id: String,
        hl: String? = null,
        @IntRange(0, 50) maxResults: Int = 5,
        onBehalfOfContentOwner: String? = null,
        pageToken: String? = null,
    ): YouTubeResponse<ChannelResponse>
}