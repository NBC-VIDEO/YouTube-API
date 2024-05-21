package com.nbc.video.presenters.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.nbc.video.AppApplication
import com.nbc.video.database.VideoDetailDatabase
import com.nbc.video.database.dao.VideoDetailDAO
import com.nbc.video.database.model.VideoDetailEntity
import com.nbc.video.network.NetworkDataSource
import com.nbc.video.network.model.video.enums.NetworkVideoPart
import com.nbc.video.presenters.detail.model.VideoDetailsModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VideoDetailViewModel(
    private val ioDispatcher: CoroutineDispatcher,
    private val videoDetailDAO: VideoDetailDAO,
    private val networkDataSource: NetworkDataSource,
) : ViewModel() {

    private val allVideos: LiveData<List<VideoDetailEntity>> = videoDetailDAO.getAll()

    fun insertVideo(video: VideoDetailEntity) = viewModelScope.launch(ioDispatcher) {
        videoDetailDAO.insertVideo(video)
    }

    fun updateIsLiked(video: VideoDetailEntity) = viewModelScope.launch(ioDispatcher) {
        videoDetailDAO.updateIsLiked(video)
    }

    fun deleteVideo(video: VideoDetailEntity) = viewModelScope.launch(ioDispatcher) {
        videoDetailDAO.deleteVideo(video)
    }

    fun getAllVideos(): LiveData<List<VideoDetailEntity>> {
        return allVideos
    }

    private val _videoDetailModelLiveData: MutableLiveData<VideoDetailsModel> = MutableLiveData()
    val videoDetailModelLiveData: LiveData<VideoDetailsModel> get() = _videoDetailModelLiveData

    fun updateVideoDetailModel(id: String) {
        try {
            viewModelScope.launch(ioDispatcher) {
                val response = networkDataSource.getVideos(
                    listOf(NetworkVideoPart.SNIPPET, NetworkVideoPart.STATISTICS),
                    id = id
                )
                val channelResponse = networkDataSource.getVideoIdChannel(
                    id = response.items.firstOrNull()?.snippet?.channelId ?: ""
                )
                channelResponse.items.firstOrNull()?.let {
                    _videoDetailModelLiveData.postValue(response.asExterminalModel(it))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("SearchFragment", "API call failed", e)
        }
    }

    companion object {
        @Suppress("UNCHECKED_CAST")
        val viewModelFactory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val application =
                    checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                val database = VideoDetailDatabase.getInstance(application)

                val ioDispatcher = Dispatchers.IO

                return VideoDetailViewModel(
                    ioDispatcher = ioDispatcher,
                    videoDetailDAO = database.videoDetailDAO(),
                    networkDataSource = (application as AppApplication).networkDataSource
                ) as T
            }
        }
    }
}