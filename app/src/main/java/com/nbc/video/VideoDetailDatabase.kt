package com.nbc.video

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [videoDetailEntity::class], version = 1)
abstract class VideoDetailDatabase : RoomDatabase() {
    abstract fun videoDetailDAO(): VideoDetailDAO

    companion object {
        private var instance: VideoDetailDatabase? = null

        @Synchronized
        fun getInstance(context: Context): VideoDetailDatabase {
            if (instance == null) {

                instance = Room.databaseBuilder(
                    context.applicationContext,
                    VideoDetailDatabase::class.java,
                    "video_detail_database"
                ).build()

            }
            return instance as VideoDetailDatabase
        }

        fun destroyInstance() {
            instance = null
        }
    }
}