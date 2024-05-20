package com.nbc.video.presenters.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.nbc.video.database.dao.VideoDetailDAO
import com.nbc.video.database.VideoDetailDatabase
import com.nbc.video.database.model.VideoDetailEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// AndroidViewModel -> ViewModel 바꾸기
class VideoDetailViewModel(
    private val ioDispatcher: CoroutineDispatcher,
    private val videoDetailDAO: VideoDetailDAO,
) : ViewModel() {

    private val allVideos: LiveData<List<VideoDetailEntity>> = videoDetailDAO.getAll()

    fun insertVideo(video: VideoDetailEntity) = viewModelScope.launch(ioDispatcher) {
        videoDetailDAO.insertVideo(video)
    }

    fun updateIsLiked(video: VideoDetailEntity) = viewModelScope.launch(ioDispatcher) {
        videoDetailDAO.updateIsLiked(video)
    }

    fun  deleteVideo(video: VideoDetailEntity) = viewModelScope.launch(ioDispatcher) {
        videoDetailDAO.deleteVideo(video)
    }

    fun getAllVideos(): LiveData<List<VideoDetailEntity>> {
        return allVideos
    }

    companion object {
        @Suppress("UNCHECKED_CAST")
        val viewModelFactory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val application = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                val database = VideoDetailDatabase.getInstance(application)

                val ioDispatcher = Dispatchers.IO

                return VideoDetailViewModel(
                    ioDispatcher = ioDispatcher,
                    videoDetailDAO = database.videoDetailDAO()
                ) as T
            }
        }
    }
}