package com.nbc.video.presenters.my_video.model

data class User(
    val name: String,
    val introduction: String,
    val favoriteVideos: List<MyVideo>,
)