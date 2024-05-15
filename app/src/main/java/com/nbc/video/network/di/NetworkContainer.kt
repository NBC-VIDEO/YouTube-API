package com.nbc.video.network.di

import com.nbc.video.BuildConfig
import com.nbc.video.network.BASE_URL
import com.nbc.video.network.CategoriesRemoteDataSource
import com.nbc.video.network.ChannelRemoteDataSource
import com.nbc.video.network.NetworkDataSource
import com.nbc.video.network.SearchRemoteDataSource
import com.nbc.video.network.VideoRemoteDataSource
import com.nbc.video.network.retrofit.NetworkApi
import com.nbc.video.network.retrofit.RetrofitService
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkContainer {

    private val callFactory: Call.Factory =
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

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .callFactory(callFactory)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val networkApi = retrofit.create(NetworkApi::class.java)

    fun bindNetworkDataSource(): NetworkDataSource {
        return RetrofitService(networkApi)
    }

    fun bindVideoRemoteDataSource(): VideoRemoteDataSource {
        return RetrofitService(networkApi)
    }

    fun bindSearchRemoteDataSource(): SearchRemoteDataSource {
        return RetrofitService(networkApi)
    }

    fun bindCategoriesRemoteDataSource(): CategoriesRemoteDataSource {
        return RetrofitService(networkApi)
    }

    fun bindChannelRemoteDataSource(): ChannelRemoteDataSource {
        return RetrofitService(networkApi)
    }
}