package dev.duckbuddyy.shopr.product_detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.duckbuddyy.shopr.domain.ShoprRepository
import dev.duckbuddyy.shopr.model.ProductDetail
import dev.duckbuddyy.shopr.network.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val shoprRepository: ShoprRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val arguments = ProductDetailFragmentArgs.fromSavedStateHandle(savedStateHandle)

    private val _productDetailFlow = MutableStateFlow<ProductDetail?>(null)
    val productDetailFlow = _productDetailFlow.asStateFlow()

    private val _loadingFlow = MutableStateFlow(true)
    val loadingFlow = _loadingFlow.asStateFlow()

    private val _hasErrorFlow = MutableStateFlow(false)
    val hasErrorFlow = _hasErrorFlow.asStateFlow()

    init {
        getProductDetail()
    }

    private fun getProductDetail(
        productId: String = arguments.productId,
        useCache: Boolean = true
    ) = viewModelScope.launch(Dispatchers.IO) {
        _loadingFlow.emit(true)
        _hasErrorFlow.emit(false)

        shoprRepository.getProductDetail(
            productId = productId,
            useCache = useCache
        ).onSuccess { productDetail ->
            _productDetailFlow.emit(productDetail)
        }.onFailure {
            _hasErrorFlow.emit(true)
            if (BuildConfig.DEBUG) {
                Log.e(this::class.simpleName, it.message.orEmpty())
            }
        }

        _loadingFlow.emit(false)
    }

    fun refreshProductDetail() = getProductDetail(useCache = false)
}