data class Search(
    val items : List<Item>
) {
    data class Item(
        val title: String,
        val thumbnail : Thumbnail,
        val views : Long
    )
}

data class Thumbnail(
    val default: Image,
    val medium: Image,
    val high: Image,
)

data class Image(
    val url: String,
    val width: Int,
    val high: Int
)