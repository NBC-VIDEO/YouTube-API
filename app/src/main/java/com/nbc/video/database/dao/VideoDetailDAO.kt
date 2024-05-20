package com.nbc.video.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nbc.video.database.model.videoDetailEntity

@Dao
interface VideoDetailDAO {

    // 새로운 VideoDetailEntity 추가
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideo(videoDetailEntity: videoDetailEntity)

    // 모든 VideoDetailEntity 가져오기
    @Query("Select * FROM video_detail")
    fun getAll() : LiveData<List<videoDetailEntity>>

    // channelId 에 해당하는 값 가져오기
    @Query("SELECT * FROM video_detail WHERE channelId = :channelId")
    suspend fun getVideoByChannelId(channelId : String) : videoDetailEntity

    @Update
    suspend fun updateIsLiked(videoDetailEntity: videoDetailEntity)

    // VideoDetailEntity 삭제
    @Delete
    suspend fun deleteVideo(videoDetailEntity: videoDetailEntity)
}