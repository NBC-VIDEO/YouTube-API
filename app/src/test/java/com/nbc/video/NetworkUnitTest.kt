package com.nbc.video

import com.nbc.video.network.NetworkDataSource
import com.nbc.video.network.di.NetworkContainer
import com.nbc.video.network.model.search.enums.NetworkSearchVideoCaption
import com.nbc.video.network.model.video.enums.NetworkVideoChart
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NetworkUnitTest {

    private lateinit var networkDataSource: NetworkDataSource

    @Before
    fun init() {
        networkDataSource = NetworkContainer().bindNetworkDataSource()
    }

    @Test
    fun `Youtube API MOST_POPULAR 비디오 리스트 가져오기`() = runTest {
        val response = networkDataSource.getVideos(
            chart = NetworkVideoChart.MOST_POPULAR, // MOST_POPULAR,
        )
        printPrettyJson(response)
    }

    @Test
    fun `Youtube API 아이디로 비디오 검색`() = runTest {
        val response = networkDataSource.getVideos(
            id = "1l7GnhW7TXY"
        )
        printPrettyJson(response)
    }

    @Test
    fun `Youtube API 카테고리 선택 후 비디오 리스트 가져오기`() = runTest {
        val response = networkDataSource.getVideos(
            chart = NetworkVideoChart.MOST_POPULAR,
             videoCategoryId = "17" // 카테고리 선택,
        )
        printPrettyJson(response)
    }

    @Test
    fun `Youtube API 검색된 비디오 리스트 가져오기`() = runTest {
        val response = networkDataSource.searchVideos(
            query = "아이브" // 검색
        )
        printPrettyJson(response)
    }

    @Test
    fun `Youtube API 아이디별 비디오 카테고리 리스트 가져오기`() = runTest {
        val response = networkDataSource.getVideoIdCategories(
            id = "38"
        )
        printPrettyJson(response)
    }

    @Test
    fun `Youtube API 국가별 비디오 카테고리 리스트 가져오기`() = runTest {
        val response = networkDataSource.getVideoRegionCodeCategories(
            regionCode = "kr" // 국가 코드 선택: en, kr
        )
        printPrettyJson(response)
    }

    @Test
    fun `Youtube API 카테고리 채널 목록 가져오기`() = runTest {
        val response = networkDataSource.getVideoIdChannel(
            id = "UCGVPdYKdBMXRs3W0GTen3lA"
        )
        printPrettyJson(response)
    }
}