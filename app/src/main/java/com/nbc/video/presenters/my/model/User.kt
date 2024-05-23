package com.nbc.video.presenters.my.model

data class User(
    val name: String,
    val introduction: String,
    val favoriteVideos: List<MyVideo>,
)