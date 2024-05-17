package com.nbc.video

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// AndroidViewModel -> ViewModel 바꾸기
class VideoDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val videoDetailDAO: VideoDetailDAO
    private val allVideos: LiveData<List<videoDetailEntity>>

    init {
        val database = VideoDetailDatabase.getInstance(application)
        videoDetailDAO = database.videoDetailDAO()
        allVideos = videoDetailDAO.getAll()
    }

    fun insertVideo(video: videoDetailEntity) = viewModelScope.launch(Dispatchers.IO) {
        videoDetailDAO.insertVideo(video)
    }

    fun updateIsLiked(video: videoDetailEntity) = viewModelScope.launch(Dispatchers.IO) {
        videoDetailDAO.updateIsLiked(video)
    }

    fun  deleteVideo(video: videoDetailEntity) = viewModelScope.launch(Dispatchers.IO) {
        videoDetailDAO.deleteVideo(video)
    }

    fun getAllVideos(): LiveData<List<videoDetailEntity>> {
        return allVideos
    }
}