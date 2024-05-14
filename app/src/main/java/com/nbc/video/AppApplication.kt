package com.nbc.video

import android.app.Application
import com.nbc.video.network.di.NetworkContainer

class AppApplication: Application() {

    val networkDataSource by lazy {
        NetworkContainer().bindNetworkDataSource()
    }
}