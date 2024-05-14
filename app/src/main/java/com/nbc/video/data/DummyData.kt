package com.nbc.video.data

// 싱글톤 객체 정의
object DummyData {
    val homeData: Home by lazy {
        createDummyHomeData()
    }

    private fun createDummyHomeData(): Home {
        // 아이템 리스트 생성
        val items = listOf(
            Item("Video 1", "https://example.com/thumb1.jpg"),
            Item("Video 2", "https://example.com/thumb2.jpg"),
            Item("Video 3", "https://example.com/thumb3.jpg")
        )

        // 카테고리 리스트 생성
        val categories = listOf(
            Category("Movies"),
            Category("Sports"),
            Category("Music")
        )

        // Home 객체 반환
        return Home(items, categories)
    }
}


