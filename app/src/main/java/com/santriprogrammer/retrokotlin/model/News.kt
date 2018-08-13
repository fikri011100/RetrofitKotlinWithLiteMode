package com.santriprogrammer.retrokotlin.model

class News {

    data class Result(
            var status: String = "",
            var articles: List<Item>
    )

    data class Item(
            var author: String = "",
            var title: String = "",
            var description: String = "",
            var url: String = "",
            var urlToImage: String = "",
            var publishedAt: String = ""
    )

}