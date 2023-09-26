package dev.duckbuddyy.shopr.product_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.duckbuddyy.shopr.domain.ShoprRepository
import dev.duckbuddyy.shopr.model.ProductDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val shoprRepository: ShoprRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val arguments = ProductDetailFragmentArgs.fromSavedStateHandle(savedStateHandle)

    private val _productDetailFlow = MutableStateFlow<ProductDetail?>(null)
    val productDetailFlow = _productDetailFlow.asStateFlow()

    private val _loadingFlow = MutableStateFlow(true)
    val loadingFlow = _loadingFlow.asStateFlow()

    private val _hasErrorFlow = MutableStateFlow(false)
    val hasErrorFlow = _hasErrorFlow.asStateFlow()

    init {
        val productId = arguments.productId
        getProductDetail(productId)
    }

    fun getProductDetail(productId: String) = viewModelScope.launch(Dispatchers.IO) {
        _loadingFlow.emit(true)
        _hasErrorFlow.emit(false)

        shoprRepository.getProductDetail(
            productId = productId
        ).onSuccess { productDetail ->
            _productDetailFlow.emit(productDetail)
        }.onFailure {
            _hasErrorFlow.emit(true)
        }

        _loadingFlow.emit(false)
    }

}