package com.nbc.video

import android.app.Application
import com.nbc.video.network.di.NetworkContainer

class AppApplication: Application() {

    private val networkDataSource by lazy { NetworkContainer() }

    val videoRemoteDataSource by lazy { networkDataSource.bindVideoRemoteDataSource() }
    val searchRemoteDataSource by lazy { networkDataSource.bindSearchRemoteDataSource() }
    val categoryRemoteDataSource by lazy { networkDataSource.bindCategoriesRemoteDataSource() }
    val channelRemoteDataSource by lazy { networkDataSource.bindChannelRemoteDataSource() }
}