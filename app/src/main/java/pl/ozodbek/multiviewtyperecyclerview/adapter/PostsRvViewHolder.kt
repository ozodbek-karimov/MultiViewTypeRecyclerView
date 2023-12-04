package pl.ozodbek.multiviewtyperecyclerview.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import pl.ozodbek.multiviewtyperecyclerview.databinding.EmptySpaceBinding
import pl.ozodbek.multiviewtyperecyclerview.databinding.TitlesRowItemBinding
import pl.ozodbek.multiviewtyperecyclerview.databinding.TopMastersRowItemBinding
import pl.ozodbek.multiviewtyperecyclerview.databinding.TopPostsRowItemBinding
import pl.ozodbek.multiviewtyperecyclerview.utils.loadImage
import pl.ozodbek.multiviewtyperecyclerview.utils.onClick
import pl.ozodbek.multiviewtyperecyclerview.utils.viewBinding


sealed class PostsRvViewHolder(binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    var itemClickListener: ((view: View, item: Any, position: Int) -> Unit)? = null

    abstract fun bind(data: PostsRvItem)


    class TitlesViewHolder private constructor(private val binding: TitlesRowItemBinding) :
        PostsRvViewHolder(binding) {

        override fun bind(data: PostsRvItem) {
            val title = data as PostsRvItem.Titles

            binding.titleTv.text = title.title
        }

        companion object {
            fun create(parent: ViewGroup): TitlesViewHolder {
                return TitlesViewHolder(parent.viewBinding(TitlesRowItemBinding::inflate))
            }
        }

    }


    class TopPostsViewHolder private constructor(private val binding: TopPostsRowItemBinding) :
        PostsRvViewHolder(binding) {

        override fun bind(data: PostsRvItem) {
            val topPosts = data as PostsRvItem.TopPosts

            binding.topPostImageView.loadImage(topPosts.image)

            binding.container.onClick {
                itemClickListener?.invoke(it, topPosts, adapterPosition)
            }
        }

        companion object {
            fun create(parent: ViewGroup): TopPostsViewHolder {
                return TopPostsViewHolder(parent.viewBinding(TopPostsRowItemBinding::inflate))
            }
        }


    }


    class TopMastersViewHolder private constructor(private val binding: TopMastersRowItemBinding) :
        PostsRvViewHolder(binding) {

        override fun bind(data: PostsRvItem) {
            val topMasters = data as PostsRvItem.TopMasters
            binding.imageView.loadImage(topMasters.image)
            binding.firstnameTv.text = topMasters.firstName
            binding.lastNameTv.text = topMasters.lastName
            binding.completedTasksTv.text = topMasters.completedTasks
            binding.container.onClick {
                itemClickListener?.invoke(it, topMasters, adapterPosition)
            }
        }

        companion object {
            fun create(parent: ViewGroup): TopMastersViewHolder {
                return TopMastersViewHolder(parent.viewBinding(TopMastersRowItemBinding::inflate))
            }
        }
    }


    class EmptySpaceViewHolder private constructor(binding: EmptySpaceBinding) :
        PostsRvViewHolder(binding) {

        override fun bind(data: PostsRvItem) {}

        companion object {
            fun create(parent: ViewGroup): EmptySpaceViewHolder {
                return EmptySpaceViewHolder(parent.viewBinding(EmptySpaceBinding::inflate))
            }
        }

    }

}