package com.nbc.video

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// AndroidViewModel -> ViewModel 바꾸기
class VideoDetailViewModel(
    private val ioDispatcher: CoroutineDispatcher,
    private val videoDetailDAO: VideoDetailDAO,
) : ViewModel() {

    private val allVideos: LiveData<List<videoDetailEntity>> = videoDetailDAO.getAll()

    fun insertVideo(video: videoDetailEntity) = viewModelScope.launch(ioDispatcher) {
        videoDetailDAO.insertVideo(video)
    }

    fun updateIsLiked(video: videoDetailEntity) = viewModelScope.launch(ioDispatcher) {
        videoDetailDAO.updateIsLiked(video)
    }

    fun  deleteVideo(video: videoDetailEntity) = viewModelScope.launch(ioDispatcher) {
        videoDetailDAO.deleteVideo(video)
    }

    fun getAllVideos(): LiveData<List<videoDetailEntity>> {
        return allVideos
    }

    companion object {
        @Suppress("UNCHECKED_CAST")
        val viewModelFactory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val application = checkNotNull(extras[APPLICATION_KEY])
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