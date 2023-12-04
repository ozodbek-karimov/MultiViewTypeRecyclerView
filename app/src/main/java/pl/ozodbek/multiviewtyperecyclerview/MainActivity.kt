package pl.ozodbek.multiviewtyperecyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pl.ozodbek.multiviewtyperecyclerview.adapter.PostsRvAdapter
import pl.ozodbek.multiviewtyperecyclerview.adapter.PostsRvItem
import pl.ozodbek.multiviewtyperecyclerview.databinding.ActivityMainBinding
import pl.ozodbek.multiviewtyperecyclerview.utils.viewBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val postsRvAdapter: PostsRvAdapter by lazy { PostsRvAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.postsRecyclerview.adapter = postsRvAdapter

        postsRvAdapter.itemClickListener = { view, item, position ->
            when (item) {
                is PostsRvItem.TopPosts -> {

                }

                is PostsRvItem.TopMasters -> {

                }
            }
        }


    }

    private fun generateMultiViewTypeRecyclerview(): List<PostsRvItem> {
        val items = mutableListOf<PostsRvItem>()

            items.add(
                PostsRvItem.Titles(
                    1,
                    "Top Posts"
                )
            )

            items.addAll(generateTopPosts())

            items.add(PostsRvItem.EmptySpace(1))

        items.add(
            PostsRvItem.Titles(
                2,
                "Top Masters"
            )
        )


        items.add(SearchFragmentRvItem.RecommendTitles(2, getString(R.string.recommended_topics)))

        val recommendedTopics = generateRecommendedTopicTitle()

        val nonEmptyRecommendedTopics = recommendedTopics.filter { it.recomendedItem.isNotEmpty() }
        if (nonEmptyRecommendedTopics.isNotEmpty()) {
            items.addAll(nonEmptyRecommendedTopics.map { SearchFragmentRvItem.RecommendedTopics(it.recomendedItem) })
            items.add(SearchFragmentRvItem.EmptySpace(2))
        }

        return items
    }
    private fun generateTopPosts(): List<PostsRvItem.TopPosts>{
        val imagesPath = listOf(
            "https://picsum.photos/100/300",
            "https://picsum.photos/101/300",
        )
        return imagesPath.map { PostsRvItem.TopPosts(it) }
    }

    private fun generateTopPosts(): List<PostsRvItem.TopPosts>{
        val imagesPath = listOf(
            "https://picsum.photos/100/300",
            "https://picsum.photos/101/300",
        )
        return imagesPath.map { PostsRvItem.TopPosts(it) }
    }
}