package com.nbc.video.presenters.home.data

import com.nbc.video.presenters.home.model.CategoryVideo
import com.nbc.video.presenters.home.model.ChannelVideo
import com.nbc.video.presenters.home.model.Image
import com.nbc.video.presenters.home.model.PopularVideo
import com.nbc.video.presenters.home.model.Thumbnail
import com.nbc.video.presenters.home.model.VideoHomeModel

// 싱글톤 객체 정의


object HomeDummyData {
    private lateinit var _video: VideoHomeModel
    val video: VideoHomeModel get() = _video
    fun getDummyPopularVideos(): List<PopularVideo> {
        return listOf(
            PopularVideo(
                thumbnails = Thumbnail(
                    default = Image(
                        "https://cdn.pixabay.com/photo/2018/10/01/09/21/pets-3715733_1280.jpg",
                        120,
                        90
                    ),
                    medium = Image(
                        "https://cdn.pixabay.com/photo/2018/10/01/09/21/pets-3715733_1280.jpg",
                        320,
                        180
                    ),
                    heigh = Image(
                        "https://cdn.pixabay.com/photo/2018/10/01/09/21/pets-3715733_1280.jpg",
                        480,
                        360
                    )
                ),
                title = "Popular Video 1", id = 10
            ),
            PopularVideo(
                thumbnails = Thumbnail(
                    default = Image(
                        "https://cdn.pixabay.com/photo/2016/09/05/21/37/cat-1647775_1280.jpg",
                        120,
                        90
                    ),
                    medium = Image(
                        "https://cdn.pixabay.com/photo/2016/09/05/21/37/cat-1647775_1280.jpg",
                        320,
                        180
                    ),
                    heigh = Image(
                        "https://cdn.pixabay.com/photo/2016/09/05/21/37/cat-1647775_1280.jpg",
                        480,
                        360
                    )
                ),
                title = "Popular Video 2", id = 10
            ),
            PopularVideo(
                thumbnails = Thumbnail(
                    default = Image(
                        "https://cdn.pixabay.com/photo/2016/09/05/21/37/cat-1647775_1280.jpg",
                        120,
                        90
                    ),
                    medium = Image(
                        "https://cdn.pixabay.com/photo/2016/09/05/21/37/cat-1647775_1280.jpg",
                        320,
                        180
                    ),
                    heigh = Image(
                        "https://cdn.pixabay.com/photo/2016/09/05/21/37/cat-1647775_1280.jpg",
                        480,
                        360
                    )
                ),
                title = "Popular Video 3", id = 10
            ),
            PopularVideo(
                thumbnails = Thumbnail(
                    default = Image(
                        "https://cdn.pixabay.com/photo/2016/09/05/21/37/cat-1647775_1280.jpg",
                        120,
                        90
                    ),
                    medium = Image(
                        "https://cdn.pixabay.com/photo/2016/09/05/21/37/cat-1647775_1280.jpg",
                        320,
                        180
                    ),
                    heigh = Image(
                        "https://cdn.pixabay.com/photo/2016/09/05/21/37/cat-1647775_1280.jpg",
                        480,
                        360
                    )
                ),
                title = "Popular Video 4", id = 10
            )
        )
    }

    fun getDummyCategoryVideos(): List<CategoryVideo> {
        return listOf(
            CategoryVideo(
                thumbnails = Thumbnail(
                    default = Image(
                        "https://cdn.pixabay.com/photo/2016/09/05/21/37/cat-1647775_1280.jpg",
                        120,
                        90
                    ),
                    medium = Image(
                        "https://cdn.pixabay.com/photo/2016/09/05/21/37/cat-1647775_1280.jpg",
                        320,
                        180
                    ),
                    heigh = Image(
                        "https://cdn.pixabay.com/photo/2016/09/05/21/37/cat-1647775_1280.jpg",
                        480,
                        360
                    )
                ),
                title = "Category Video 1", id = 10
            ),
            CategoryVideo(
                thumbnails = Thumbnail(
                    default = Image(
                        "https://cdn.pixabay.com/photo/2016/09/05/21/37/cat-1647775_1280.jpg",
                        120,
                        90
                    ),
                    medium = Image(
                        "https://cdn.pixabay.com/photo/2016/09/05/21/37/cat-1647775_1280.jpg",
                        320,
                        180
                    ),
                    heigh = Image(
                        "https://cdn.pixabay.com/photo/2016/09/05/21/37/cat-1647775_1280.jpg",
                        480,
                        360
                    )
                ),
                title = "Category Video 2", id = 10
            ),
            CategoryVideo(
                thumbnails = Thumbnail(
                    default = Image(
                        "https://cdn.pixabay.com/photo/2016/09/05/21/37/cat-1647775_1280.jpg",
                        120,
                        90
                    ),
                    medium = Image(
                        "https://cdn.pixabay.com/photo/2016/09/05/21/37/cat-1647775_1280.jpg",
                        320,
                        180
                    ),
                    heigh = Image(
                        "https://cdn.pixabay.com/photo/2016/09/05/21/37/cat-1647775_1280.jpg",
                        480,
                        360
                    )
                ),
                title = "Category Video 3", id = 10
            ),
            CategoryVideo(
                thumbnails = Thumbnail(
                    default = Image(
                        "https://cdn.pixabay.com/photo/2016/09/05/21/37/cat-1647775_1280.jpg",
                        120,
                        90
                    ),
                    medium = Image(
                        "https://cdn.pixabay.com/photo/2016/09/05/21/37/cat-1647775_1280.jpg",
                        320,
                        180
                    ),
                    heigh = Image(
                        "https://cdn.pixabay.com/photo/2016/09/05/21/37/cat-1647775_1280.jpg",
                        480,
                        360
                    )
                ),
                title = "Category Video 4", id = 10
            )
        )
    }

    fun getDummyChannelVideos(): List<ChannelVideo> {
        return listOf(
            ChannelVideo(
                thumbnails = Thumbnail(
                    default = Image(
                        "https://cdn.pixabay.com/photo/2016/09/05/21/37/cat-1647775_1280.jpg",
                        120,
                        90
                    ),
                    medium = Image(
                        "https://cdn.pixabay.com/photo/2016/09/05/21/37/cat-1647775_1280.jpg",
                        320,
                        180
                    ),
                    heigh = Image(
                        "https://cdn.pixabay.com/photo/2016/09/05/21/37/cat-1647775_1280.jpg",
                        480,
                        360
                    )
                ),
                title = "Channel Video 1", id = 10
            ),
            ChannelVideo(
                thumbnails = Thumbnail(
                    default = Image(
                        "https://cdn.pixabay.com/photo/2016/09/05/21/37/cat-1647775_1280.jpg",
                        120,
                        90
                    ),
                    medium = Image(
                        "https://cdn.pixabay.com/photo/2016/09/05/21/37/cat-1647775_1280.jpg",
                        320,
                        180
                    ),
                    heigh = Image(
                        "https://cdn.pixabay.com/photo/2016/09/05/21/37/cat-1647775_1280.jpg",
                        480,
                        360
                    )
                ),
                title = "Channel Video 2", id = 10
            ),
            ChannelVideo(
                thumbnails = Thumbnail(
                    default = Image(
                        "https://cdn.pixabay.com/photo/2016/09/05/21/37/cat-1647775_1280.jpg",
                        120,
                        90
                    ),
                    medium = Image(
                        "https://cdn.pixabay.com/photo/2016/09/05/21/37/cat-1647775_1280.jpg",
                        320,
                        180
                    ),
                    heigh = Image(
                        "https://cdn.pixabay.com/photo/2016/09/05/21/37/cat-1647775_1280.jpg",
                        480,
                        360
                    )
                ),
                title = "Channel Video 3", id = 10
            ),
            ChannelVideo(
                thumbnails = Thumbnail(
                    default = Image(
                        "https://cdn.pixabay.com/photo/2016/09/05/21/37/cat-1647775_1280.jpg",
                        120,
                        90
                    ),
                    medium = Image(
                        "https://cdn.pixabay.com/photo/2016/09/05/21/37/cat-1647775_1280.jpg",
                        320,
                        180
                    ),
                    heigh = Image(
                        "https://cdn.pixabay.com/photo/2016/09/05/21/37/cat-1647775_1280.jpg",
                        480,
                        360
                    )
                ),
                title = "Channel Video 4", id = 10
            )
        )
    }

    fun getDummyVideoHomeModel(): VideoHomeModel {
        return VideoHomeModel(
            popularVideo = getDummyPopularVideos(),
            categoryVideo = getDummyCategoryVideos(),
            channelVideo = getDummyChannelVideos()
        )
    }
}