package pl.ozodbek.multiviewtyperecyclerview.adapter

import pl.ozodbek.multiviewtyperecyclerview.data.TopRecommendedsList

sealed interface PostsRvItem {


    data class TopPosts(
        val id: Int,
        val image: String,
    ) : PostsRvItem


    data class TopRecommendeds(
        val topMasters: TopRecommendedsList
    ) : PostsRvItem

    data class Titles(val id: Int, val title: String) : PostsRvItem

    data class EmptySpace(val id: Int) : PostsRvItem

}