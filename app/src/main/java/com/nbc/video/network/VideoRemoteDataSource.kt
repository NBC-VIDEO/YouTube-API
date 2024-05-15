package com.nbc.video.network

import androidx.annotation.IntRange
import com.nbc.video.network.model.VideoResponse
import com.nbc.video.network.model.YouTubeResponse
import com.nbc.video.network.model.video.enums.NetworkVideoChart
import com.nbc.video.network.model.video.enums.NetworkVideoPart

interface VideoRemoteDataSource {

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
}