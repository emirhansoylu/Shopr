package dev.duckbuddyy.shopr.domain

import android.widget.ImageView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import dev.duckbuddyy.shopr.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

inline fun <T> Flow<T>.collectLatestWhenStarted(
    viewLifecycleOwner: LifecycleOwner,
    crossinline collector: suspend (T) -> Unit
) = viewLifecycleOwner.lifecycleScope.launch {
    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
        collectLatest {
            collector(it)
        }
    }
}

fun ImageView.load(imageUrl: String) {
    val cornerRadius = resources.getDimension(R.dimen.corner_radius).toInt()
    Glide
        .with(context)
        .load(imageUrl)
        .transform(CenterInside(), RoundedCorners(cornerRadius))
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}