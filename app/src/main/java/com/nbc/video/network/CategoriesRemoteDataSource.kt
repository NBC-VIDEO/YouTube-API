package com.nbc.video.network

import com.nbc.video.network.model.VideoCategoriesResponse
import com.nbc.video.network.model.YouTubeResponse

interface CategoriesRemoteDataSource {
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
}