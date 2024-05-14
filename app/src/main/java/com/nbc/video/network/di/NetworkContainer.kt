package com.nbc.video.network.di

import com.nbc.video.BuildConfig
import com.nbc.video.network.NetworkDataSource
import com.nbc.video.network.retrofit.RetrofitService
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class NetworkContainer {

    private fun provideCallFactory(): Call.Factory =
        OkHttpClient.Builder().run {
            if (BuildConfig.DEBUG) {
                addNetworkInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                )
            }
            build()
        }

    fun bindNetworkDataSource(): NetworkDataSource {
        return RetrofitService(provideCallFactory())
    }
}