package dev.duckbuddyy.shopr.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.duckbuddyy.shopr.domain.ShoprRepository
import dev.duckbuddyy.shopr.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartsViewModel @Inject constructor(
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

    fun getProducts() = viewModelScope.launch {
        shoprRepository.getCart().onSuccess { cart ->
            cart.products.forEach {
                println(it)
            }
        }
    }

}