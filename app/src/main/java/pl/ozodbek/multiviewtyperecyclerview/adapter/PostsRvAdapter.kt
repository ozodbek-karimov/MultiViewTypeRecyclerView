package pl.ozodbek.multiviewtyperecyclerview.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import pl.ozodbek.multiviewtyperecyclerview.R

class PostsRvAdapter :
    ListAdapter<PostsRvItem, PostsRvViewHolder>(SearchRecyclerViewDiffUtil()) {

    var itemClickListener: ((view: View, item: Any, position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsRvViewHolder {
        return when (viewType) {

            R.layout.top_posts_row_item -> PostsRvViewHolder.TopPostsViewHolder.create(
                parent
            )
            R.layout.top_masters_row_item -> PostsRvViewHolder.TopMastersViewHolder.create(
                parent
            )
            R.layout.titles_row_item -> PostsRvViewHolder.TitlesViewHolder.create(
                parent
            )
            R.layout.empty_space -> PostsRvViewHolder.EmptySpaceViewHolder.create(parent)

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
            is PostsRvItem.TopPosts -> R.layout.top_posts_row_item
            is PostsRvItem.TopMasters -> R.layout.top_masters_row_item
            is PostsRvItem.Titles -> R.layout.titles_row_item
            is PostsRvItem.EmptySpace -> R.layout.empty_space
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