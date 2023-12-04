package pl.ozodbek.multiviewtyperecyclerview.adapter

sealed interface PostsRvItem {


    data class TopPosts(
        val image: String,
    ) : PostsRvItem


    data class TopMasters(
        val id: Int,
        val image: String,
        val firstName: String,
        val lastName: String,
        val completedTasks: String,

        ) : PostsRvItem

    data class Titles(val id: Int, val title: String) : PostsRvItem

    data class EmptySpace(val id: Int) : PostsRvItem

}