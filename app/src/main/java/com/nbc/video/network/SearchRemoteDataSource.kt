package com.nbc.video.network

import androidx.annotation.IntRange
import com.nbc.video.network.model.SearchResponse
import com.nbc.video.network.model.YouTubeResponse
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

interface SearchRemoteDataSource {

    /**
     * 매개변수 설명
     *
     * [part]: API 응답에 포함될 하나 이상의 video 리소스 속성의 쉼표로 구분된 목록을 지정합니다.
     * 매개변수가 하위 속성을 포함하는 속성을 식별하는 경우 하위 속성이 응답에 포함됩니다.
     *
     * [channelId]: channelId 매개변수는 API 응답이 채널에서 만든 리소스만 포함해야 함을 나타냅니다.
     *
     * [channelType]: channelType 매개변수를 사용하면 특정 유형의 채널로 검색을 제한할 수 있습니다.
     *
     * [eventType]: eventType 매개변수는 브로드캐스트 이벤트로 검색을 제한합니다.
     *
     * [location]: location 매개변수는 locationRadius 매개변수와 함께 원형의 지리적 영역을 정의하며, 메타데이터에서 해당 지역에 속하는 지리적 위치를 지정하는 동영상으로 검색을 제한합니다.
     *
     * [locationRadius]: locationRadius 매개변수는 location 매개변수와 함께 원형의 지리적 영역을 정의합니다.
     *
     * [maxResults]: maxResults 매개변수는 결과 집합에 반환해야 하는 최대 항목 수를 지정합니다.
     *
     * [onBehalfOfContentOwner]: 이 매개변수는 제대로 승인된 요청에서만 사용할 수 있습니다. 참고: 이 매개변수는 YouTube 콘텐츠 파트너 전용입니다.
     * [onBehalfOfContentOwner] 매개변수는 요청의 승인 사용자 인증 정보가 매개변수 값에 지정된 콘텐츠 소유자를 대신하는 YouTube CMS 사용자를 식별함을 나타냅니다.
     *
     * [order]: order 매개변수는 API 응답에서 리소스를 정렬하는 데 사용할 메서드를 지정합니다.
     *
     * [pageToken]: pageToken 매개변수는 반환해야 하는 결과 집합의 특정 페이지를 식별합니다.
     *
     * [publishedAfter]: publishedAfter 매개변수는 API 응답이 지정된 시간 또는 그 이후에 생성된 리소스만 포함해야 함을 나타냅니다.
     *
     * [publishedBefore]: publishedBefore 매개변수는 API 응답이 지정된 시간에 만들어진 리소스만 포함해야 함을 나타냅니다.
     *
     * [query]: q 매개변수는 검색할 검색어를 지정합니다.
     *
     * [regionCode]: regionCode 매개변수는 지정된 국가에서 볼 수 있는 동영상의 검색결과를 반환하도록 API에 지시합니다.
     *
     * [relevanceLanguage]: relevanceLanguage 매개변수는 지정된 언어와 가장 관련성이 높은 검색결과를 반환하도록 API에 지시합니다.
     *
     * [safeSearch]: safeSearch 매개변수는 검색결과에 표준 콘텐츠뿐 아니라 제한된 콘텐츠도 포함되는지 여부를 나타냅니다.
     *
     * [topicId]: topicId 매개변수는 API 응답에 지정된 주제와 연결된 리소스만 포함해야 함을 나타냅니다.
     *
     * [type]: type 매개변수는 특정 유형의 리소스만 검색하도록 검색어를 제한합니다.
     *
     * [videoCaption]: videoCaption 매개변수는 자막이 있는지에 따라 API가 동영상 검색결과를 필터링해야 하는지 여부를 나타냅니다.
     *
     * [videoCategoryId]: videoCategoryId 매개변수는 카테고리를 기준으로 동영상 검색결과를 필터
     *
     */

    suspend fun searchVideos(
        part: String = "snippet",
        channelId: String? = null,
        channelType: NetworkSearchChannelType? = null,
        eventType: NetworkSearchEventType? = null,
        location: String? = null,
        locationRadius: String? = null,
        @IntRange(0, 50) maxResults: Int = 5,
        onBehalfOfContentOwner: String? = null,
        order: NetworkSearchOrder? = null,
        pageToken: String? = null,
        publishedAfter: String? = null,
        publishedBefore: String? = null,
        query: String? = null,
        regionCode: String? = null,
        relevanceLanguage: String? = null,
        safeSearch: String? = null,
        topicId: String? = null,
        type: NetworkSearchType? = null,
        videoCaption: NetworkSearchVideoCaption? = null,
        videoCategoryId: String? = null,
        videoDefinition: NetworkSearchVideoDefinition? = null,
        videoDimension: NetworkSearchVideoDimension? = null,
        videoDuration: NetworkSearchVideoDuration? = null,
        videoEmbeddable: NetworkSearchVideoEmbeddable? = null,
        videoLicense: NetworkSearchVideoLicense? = null,
        videoPaidProductPlacement: NetworkSearchVideoPaidProductPlacement? = null,
        videoSyndicated: NetworkSearchVideoSyndicated? = null,
        videoType: NetworkSearchVideoType? = null,
    ): YouTubeResponse<SearchResponse>
}