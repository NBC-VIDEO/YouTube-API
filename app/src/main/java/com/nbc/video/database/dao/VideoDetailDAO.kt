package com.nbc.video.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nbc.video.database.model.VideoDetailEntity

@Dao
interface VideoDetailDAO {

    // 새로운 VideoDetailEntity 추가
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideo(videoDetailEntity: VideoDetailEntity)

    // 모든 VideoDetailEntity 가져오기
    @Query("Select * FROM video_detail")
    fun getAll(): LiveData<List<VideoDetailEntity>>

    // channelId 에 해당하는 값 가져오기
    @Query("SELECT * FROM video_detail WHERE channelId = :channelId")
    suspend fun getVideoByChannelId(channelId: String): VideoDetailEntity

    // 특정 아이디 아이템 가져오기
    @Query("SELECT * FROM video_detail WHERE id = :id")
    suspend fun getVideoById(id: String): VideoDetailEntity?

    @Update
    suspend fun updateIsLiked(videoDetailEntity: VideoDetailEntity)

    // VideoDetailEntity 삭제
    @Query("DELETE FROM video_detail WHERE id = :id")
    suspend fun deleteVideo(id: String)

    @Delete
    suspend fun deleteVideo(videoDetailEntity: VideoDetailEntity)
}