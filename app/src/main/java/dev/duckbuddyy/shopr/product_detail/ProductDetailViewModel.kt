package dev.duckbuddyy.shopr.product_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.duckbuddyy.shopr.domain.ShoprRepository
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

    private val _uiStateFlow = MutableStateFlow<ProductDetailState>(ProductDetailState.Loading)
    val uiStateFlow = _uiStateFlow.asStateFlow()

    init {
        getProductDetail()
    }

    private fun getProductDetail(
        productId: String = arguments.productId,
        useCache: Boolean = true
    ) = viewModelScope.launch(Dispatchers.IO) {
        _uiStateFlow.emit(ProductDetailState.Loading)

        val productDetail = shoprRepository.getProductDetail(
            productId = productId,
            useCache = useCache
        )

        if (productDetail != null) {
            _uiStateFlow.emit(ProductDetailState.Success(productDetail))
        } else {
            _uiStateFlow.emit(ProductDetailState.Error)
        }
    }

    fun refreshProductDetail() = getProductDetail(useCache = false)
}