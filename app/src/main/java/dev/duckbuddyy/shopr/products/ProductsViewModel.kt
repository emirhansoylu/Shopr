package dev.duckbuddyy.shopr.products

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
class ProductsViewModel @Inject constructor(
    private val shoprRepository: ShoprRepository
) : ViewModel() {

    private val _productsStateFlow = MutableStateFlow<ProductsState>(ProductsState.Loading)
    val productsStateFlow = _productsStateFlow.asStateFlow()

    init {
        getProducts()
    }

    private fun getProducts(
        useCache: Boolean = true
    ) = viewModelScope.launch(Dispatchers.IO) {
        _productsStateFlow.emit(ProductsState.Loading)

        val products = shoprRepository.getCart(
            useCache = useCache
        )?.products.orEmpty()

        if (products.isNotEmpty()) {
            _productsStateFlow.emit(ProductsState.Success(products))
        } else {
            _productsStateFlow.emit(ProductsState.Error)
        }
    }

    fun refreshProducts() = getProducts(useCache = false)
}