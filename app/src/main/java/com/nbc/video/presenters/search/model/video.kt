package com.nbc.video.presenters.search.model

data class Search(
    val items : List<Item>
) {
    data class Item(
        val title: String,
        val thumbnail: String,
        val views: Long
    )
}