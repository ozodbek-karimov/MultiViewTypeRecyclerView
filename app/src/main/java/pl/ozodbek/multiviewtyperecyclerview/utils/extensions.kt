package pl.ozodbek.multiviewtyperecyclerview.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import coil.load
import coil.request.CachePolicy
import coil.size.ViewSizeResolver
import pl.ozodbek.multiviewtyperecyclerview.R

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T) =
    lazy(LazyThreadSafetyMode.NONE) {
        bindingInflater.invoke(layoutInflater)
    }



fun View.getColor(color: Int): Int {
    return ContextCompat.getColor(this.context, color)
}

fun View.onClick(clickListener: (View) -> Unit) {
    setOnClickListener(clickListener)
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun <T : ViewBinding> ViewGroup.viewBinding(viewBindingFactory: (LayoutInflater, ViewGroup, Boolean) -> T) =
    viewBindingFactory.invoke(LayoutInflater.from(this.context), this, false)


fun <T> ImageView.loadImage(image: T?) {
    this.load(image.takeIf { it?.toString()?.isNotBlank() == true } ?: R.drawable.ic_error_placeholder) {
        crossfade(true)
        placeholder(R.drawable.ic_error_placeholder)
        error(R.drawable.ic_error_placeholder)
        size(ViewSizeResolver(this@loadImage))
        memoryCachePolicy(CachePolicy.ENABLED)
        diskCachePolicy(CachePolicy.ENABLED)
    }


    /** IMAGE LOADING CACHE

    val imageLoader = ImageLoader.Builder(context)
    .respectCacheHeaders(false)
    .build()
    Coil.setImageLoader(imageLoader)


     */
}

