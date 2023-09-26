package dev.duckbuddyy.shopr.domain

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
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