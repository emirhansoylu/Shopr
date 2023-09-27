package dev.duckbuddyy.shopr.products

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.duckbuddyy.shopr.domain.ShoprRepository
import dev.duckbuddyy.shopr.model.Product
import dev.duckbuddyy.shopr.network.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val shoprRepository: ShoprRepository
) : ViewModel() {

    private val _productsFlow = MutableStateFlow(emptyList<Product>())
    val productsFlow = _productsFlow.asStateFlow()

    private val _loadingFlow = MutableStateFlow(true)
    val loadingFlow = _loadingFlow.asStateFlow()

    private val _hasErrorFlow = MutableStateFlow(false)
    val hasErrorFlow = _hasErrorFlow.asStateFlow()

    init {
        getProducts()
    }

    private fun getProducts(
        useCache: Boolean = true
    ) = viewModelScope.launch(Dispatchers.IO) {
        _loadingFlow.emit(true)
        _hasErrorFlow.emit(false)
        _productsFlow.emit(emptyList())

        shoprRepository.getCart(
            useCache = useCache
        ).onSuccess { cart ->
            _productsFlow.emit(cart.products)
        }.onFailure {
            _hasErrorFlow.emit(true)
            if (BuildConfig.DEBUG) {
                Log.e(this::class.simpleName, it.message.orEmpty())
            }
        }

        _loadingFlow.emit(false)
    }

    fun refreshProducts() = getProducts(useCache = false)
}