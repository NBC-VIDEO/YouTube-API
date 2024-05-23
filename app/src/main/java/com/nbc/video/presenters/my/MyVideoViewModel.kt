package com.nbc.video.presenters.my

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import androidx.lifecycle.viewmodel.CreationExtras
import com.nbc.video.database.VideoDetailDatabase
import com.nbc.video.database.dao.VideoDetailDAO
import com.nbc.video.database.model.VideoDetailEntity
import com.nbc.video.presenters.my.model.MyVideo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MyVideoViewModel(
    private val videoDetailDAO: VideoDetailDAO,
) : ViewModel() {

    val likeVideos: LiveData<List<MyVideo>> = videoDetailDAO.getAll()
        .map {
            it.map {
                it.asExternalModel()
            }
        }

    companion object {
        @Suppress("UNCHECKED_CAST")
        val viewModelFactory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val application =
                    checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                val database = VideoDetailDatabase.getInstance(application)

                return MyVideoViewModel(
                    videoDetailDAO = database.videoDetailDAO()
                ) as T
            }
        }
    }
}

private fun VideoDetailEntity.asExternalModel(): MyVideo {
    return MyVideo(
        id = id,
        title = title,
        description = description,
        thumbnailUrl = thumbnailUrl,
        views = views,
        writtenName = writtenName,
        dateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_DATE_TIME)
    )
}
