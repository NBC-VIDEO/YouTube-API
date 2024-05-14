package com.nbc.video.data


data class Home(
    val item: List<Item>,
    val category: List<Category>
)

data class Item(
    val title: String,
    val thumbnailUrl: String
)

//카테고리 어떻게 ?
data class Category(
    val name: String
)