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

interface NetworkDataSource {

    /**
     * 매개변수 설명
     *
     * [parts]: API 응답에 포함될 하나 이상의 video 리소스 속성의 쉼표로 구분된 목록을 지정합니다.
     * 매개변수가 하위 속성을 포함하는 속성을 식별하는 경우 하위 속성이 응답에 포함됩니다.
     *
     * [chart]: 검색하려는 차트를 식별합니다.
     *
     * [hl]: YouTube 웹사이트에서 지원하는 특정 애플리케이션 언어에 대해 현지화된 리소스 메타데이터를 검색하도록 API에 지시합니다. 매개변수 값은 i18nLanguages.list 메서드에서 반환하는 목록에 포함된 언어 코드여야 합니다.
     * 현지화된 리소스 세부정보가 해당 언어로 제공되는 경우 리소스의 snippet.localized 객체에 현지화된 값이 포함됩니다. 하지만 현지화된 세부정보를 사용할 수 없는 경우 snippet.localized 객체에는 리소스의 기본 언어로 된 리소스 세부정보가 포함됩니다.
     *
     * [maxHeight]: 내장 플레이어의 최대 높이를 지정합니다. 이 매개변수를 사용하여 기본 크기 대신 소스 코드가 애플리케이션 레이아웃에 적합한 높이를 사용하도록 지정할 수 있습니다. maxWidth 매개변수도 제공되는 경우 최대 너비를 위반하지 않도록 플레이어가 maxHeight보다 짧을 수도 있습니다. 사용 가능한 값: 72~8192
     *
     * [maxResults]: 결과 집합에 반환해야 하는 최대 항목 수를 지정합니다.
     * 참고: 이 매개변수는 myRating 매개변수와 함께 사용하도록 지원되지만 id 매개변수와 함께 사용하도록 지원되지는 않습니다. 사용 가능한 값: 1~50 기본값은 5입니다.
     *
     * [maxWidth]: 내장 플레이어의 최대 너비를 지정합니다. 이 매개변수를 사용하여 기본 크기 대신 소스 코드에서 애플리케이션 레이아웃에 적합한 너비를 사용하도록 지정할 수 있습니다.
     * [maxHeight] 매개변수도 제공되는 경우 최대 높이를 위반하지 않도록 플레이어의 범위가 maxWidth보다 좁을 수도 있습니다. 사용 가능한 값: 72~8192
     *
     * [onBehalfOfContentOwner]: 이 매개변수는 제대로 승인된 요청에서만 사용할 수 있습니다. 참고: 이 매개변수는 YouTube 콘텐츠 파트너 전용입니다.
     * [onBehalfOfContentOwner] 매개변수는 요청의 승인 사용자 인증 정보가 매개변수 값에 지정된 콘텐츠 소유자를 대신하는 YouTube CMS 사용자를 식별함을 나타냅니다. 이 매개변수는 다양한 YouTube 채널을 소유하고 관리하는 YouTube 콘텐츠 파트너를 위한 것입니다. 콘텐츠 소유자가 각 채널에 사용자 인증 정보를 제공하지 않고도 한 번만 인증하면 모든 동영상 및 채널 데이터에 액세스할 수 있습니다. 사용자가 인증할 CMS 계정은 지정된 YouTube 콘텐츠 소유자에게 연결되어야 합니다.
     *
     * [pageToken]: 반환해야 하는 결과 집합의 특정 페이지를 식별합니다. API 응답에서 nextPageToken 및 prevPageToken 속성은 검색할 수 있는 다른 페이지를 식별합니다.
     * 참고: 이 매개변수는 myRating 매개변수와 함께 사용하도록 지원되지만 id 매개변수와 함께 사용하도록 지원되지는 않습니다.
     *
     * [regionCode]: 지정된 지역에서 사용할 수 있는 동영상 차트를 선택하도록 API에 지시합니다. 이 매개변수는 chart 매개변수와 함께만 사용할 수 있습니다. 이 매개변수 값은 ISO 3166-1 alpha-2 국가 코드입니다de.
     *
     * [videoCategoryId]: 차트를 검색해야 하는 동영상 카테고리를 식별합니다. 이 매개변수는 chart 매개변수와 함께만 사용할 수 있습니다. 기본적으로 차트는 특정 카테고리로 제한되지 않습니다. 기본값은 0입니다.
     */

    suspend fun getVideos(
        parts: List<NetworkVideoPart> = listOf(NetworkVideoPart.SNIPPET),
        chart: NetworkVideoChart = NetworkVideoChart.MOST_POPULAR,
        hl: String? = null,
        @IntRange(72, 8192) maxHeight: Int? = null,
        @IntRange(0, 50) maxResults: Int = 5,
        @IntRange(72, 8192) maxWidth: Int? = null,
        onBehalfOfContentOwner: String? = null,
        pageToken: String? = null,
        regionCode: String? = null,
        videoCategoryId: String? = null,
    ): YouTubeResponse<VideoResponse>

    /**
     * 매개변수 설명
     *
     * [parts]: API 응답에 포함될 하나 이상의 video 리소스 속성의 쉼표로 구분된 목록을 지정합니다.
     * 매개변수가 하위 속성을 포함하는 속성을 식별하는 경우 하위 속성이 응답에 포함됩니다.
     *
     * [id]: id 매개변수는 검색되는 리소스에 대한 YouTube 동영상 ID의 쉼표로 구분된 목록을 지정합니다. video 리소스에서 id 속성은 동영상의 ID를 지정합니다.
     *
     * [hl]: YouTube 웹사이트에서 지원하는 특정 애플리케이션 언어에 대해 현지화된 리소스 메타데이터를 검색하도록 API에 지시합니다. 매개변수 값은 i18nLanguages.list 메서드에서 반환하는 목록에 포함된 언어 코드여야 합니다.
     * 현지화된 리소스 세부정보가 해당 언어로 제공되는 경우 리소스의 snippet.localized 객체에 현지화된 값이 포함됩니다. 하지만 현지화된 세부정보를 사용할 수 없는 경우 snippet.localized 객체에는 리소스의 기본 언어로 된 리소스 세부정보가 포함됩니다.
     *
     * [maxHeight]: 내장 플레이어의 최대 높이를 지정합니다. 이 매개변수를 사용하여 기본 크기 대신 소스 코드가 애플리케이션 레이아웃에 적합한 높이를 사용하도록 지정할 수 있습니다. maxWidth 매개변수도 제공되는 경우 최대 너비를 위반하지 않도록 플레이어가 maxHeight보다 짧을 수도 있습니다. 사용 가능한 값: 72~8192
     *
     * [maxResults]: 결과 집합에 반환해야 하는 최대 항목 수를 지정합니다.
     * 참고: 이 매개변수는 myRating 매개변수와 함께 사용하도록 지원되지만 id 매개변수와 함께 사용하도록 지원되지는 않습니다. 사용 가능한 값: 1~50 기본값은 5입니다.
     *
     * [maxWidth]: 내장 플레이어의 최대 너비를 지정합니다. 이 매개변수를 사용하여 기본 크기 대신 소스 코드에서 애플리케이션 레이아웃에 적합한 너비를 사용하도록 지정할 수 있습니다.
     * [maxHeight] 매개변수도 제공되는 경우 최대 높이를 위반하지 않도록 플레이어의 범위가 maxWidth보다 좁을 수도 있습니다. 사용 가능한 값: 72~8192
     *
     * [onBehalfOfContentOwner]: 이 매개변수는 제대로 승인된 요청에서만 사용할 수 있습니다. 참고: 이 매개변수는 YouTube 콘텐츠 파트너 전용입니다.
     * [onBehalfOfContentOwner] 매개변수는 요청의 승인 사용자 인증 정보가 매개변수 값에 지정된 콘텐츠 소유자를 대신하는 YouTube CMS 사용자를 식별함을 나타냅니다. 이 매개변수는 다양한 YouTube 채널을 소유하고 관리하는 YouTube 콘텐츠 파트너를 위한 것입니다. 콘텐츠 소유자가 각 채널에 사용자 인증 정보를 제공하지 않고도 한 번만 인증하면 모든 동영상 및 채널 데이터에 액세스할 수 있습니다. 사용자가 인증할 CMS 계정은 지정된 YouTube 콘텐츠 소유자에게 연결되어야 합니다.
     *
     * [pageToken]: 반환해야 하는 결과 집합의 특정 페이지를 식별합니다. API 응답에서 nextPageToken 및 prevPageToken 속성은 검색할 수 있는 다른 페이지를 식별합니다.
     * 참고: 이 매개변수는 myRating 매개변수와 함께 사용하도록 지원되지만 id 매개변수와 함께 사용하도록 지원되지는 않습니다.
     *
     * [regionCode]: 지정된 지역에서 사용할 수 있는 동영상 차트를 선택하도록 API에 지시합니다. 이 매개변수는 chart 매개변수와 함께만 사용할 수 있습니다. 이 매개변수 값은 ISO 3166-1 alpha-2 국가 코드입니다de.
     *
     * [videoCategoryId]: 차트를 검색해야 하는 동영상 카테고리를 식별합니다. 이 매개변수는 chart 매개변수와 함께만 사용할 수 있습니다. 기본적으로 차트는 특정 카테고리로 제한되지 않습니다. 기본값은 0입니다.
     */

    suspend fun getVideos(
        parts: List<NetworkVideoPart> = listOf(NetworkVideoPart.SNIPPET),
        id: String,
        hl: String? = null,
        @IntRange(72, 8192) maxHeight: Int? = null,
        @IntRange(0, 50) maxResults: Int = 5,
        @IntRange(72, 8192) maxWidth: Int? = null,
        onBehalfOfContentOwner: String? = null,
        pageToken: String? = null,
        regionCode: String? = null,
        videoCategoryId: String? = null,
    ): YouTubeResponse<VideoResponse>

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

    /**
     * 매개변수 설명
     *
     * [part]: part 매개변수는 API 응답이 포함하는 videoCategory 리소스 속성을 지정합니다.
     * 매개변수 값을 snippet로 설정합니다.
     *
     * [id]: id 매개변수는 검색하는 리소스에 대한 동영상 카테고리 ID의 쉼표로 구분된 목록을 지정합니다.
     *
     * [hl]: hl 매개변수는 API 응답의 텍스트 값에 사용할 언어를 지정합니다.
     * 기본값은 en_US입니다.
     */

    suspend fun getVideoIdCategories(
        part: String = "snippet",
        id: String? = null,
        hl: String? = null,
    ): YouTubeResponse<VideoCategoriesResponse>

    /**
     * 매개변수 설명
     *
     * [part]: part 매개변수는 API 응답이 포함하는 videoCategory 리소스 속성을 지정합니다.
     * 매개변수 값을 snippet로 설정합니다.
     *
     * [regionCode]: regionCode 매개변수는 지정된 국가에서 제공되는 동영상 카테고리 목록을 API에서 반환하도록 합니다.
     * 매개변수 값은 ISO 3166-1 alpha-2 국가 코드입니다.
     *
     * [hl]: hl 매개변수는 API 응답의 텍스트 값에 사용할 언어를 지정합니다.
     * 기본값은 en_US입니다.
     */

    suspend fun getVideoRegionCodeCategories(
        part: String = "snippet",
        regionCode: String? = null,
        hl: String? = null,
    ): YouTubeResponse<VideoCategoriesResponse>


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