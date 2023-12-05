package pl.ozodbek.multiviewtyperecyclerview.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import pl.ozodbek.multiviewtyperecyclerview.utils.Constants.Companion.LAYOUT_EMPTY_SPACE
import pl.ozodbek.multiviewtyperecyclerview.utils.Constants.Companion.LAYOUT_TITLES
import pl.ozodbek.multiviewtyperecyclerview.utils.Constants.Companion.LAYOUT_TOP_POSTS
import pl.ozodbek.multiviewtyperecyclerview.utils.Constants.Companion.LAYOUT_TOP_RECOMMENDED

class PostsRvAdapter :
    ListAdapter<PostsRvItem, PostsRvViewHolder>(SearchRecyclerViewDiffUtil()) {

    var itemClickListener: ((view: View, item: Any, position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsRvViewHolder {
        return when (viewType) {

            LAYOUT_TOP_POSTS -> PostsRvViewHolder.TopPostsViewHolder.create(
                parent
            )

            LAYOUT_TOP_RECOMMENDED -> PostsRvViewHolder.TopMastersViewHolder.create(
                parent
            )

            LAYOUT_TITLES -> PostsRvViewHolder.TitlesViewHolder.create(
                parent
            )

            LAYOUT_EMPTY_SPACE -> PostsRvViewHolder.EmptySpaceViewHolder.create(parent)

            else -> throw IllegalArgumentException("Invalid ViewType Provided")
        }
    }

    override fun onBindViewHolder(holder: PostsRvViewHolder, position: Int) {
        holder.itemClickListener = itemClickListener
        val item = getItem(position)
        holder.bind(item)
    }


    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is PostsRvItem.TopPosts -> LAYOUT_TOP_POSTS
            is PostsRvItem.TopRecommended -> LAYOUT_TOP_RECOMMENDED
            is PostsRvItem.Titles -> LAYOUT_TITLES
            is PostsRvItem.EmptySpace -> LAYOUT_EMPTY_SPACE
        }
    }

    class SearchRecyclerViewDiffUtil : DiffUtil.ItemCallback<PostsRvItem>() {
        override fun areItemsTheSame(
            oldItem: PostsRvItem,
            newItem: PostsRvItem,
        ): Boolean {
            return oldItem.javaClass == newItem.javaClass
        }

        override fun areContentsTheSame(
            oldItem: PostsRvItem,
            newItem: PostsRvItem,
        ): Boolean {
            return oldItem == newItem
        }
    }

}