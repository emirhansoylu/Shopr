package dev.duckbuddyy.shopr.products

import dev.duckbuddyy.shopr.model.Product

sealed class ProductsState {
    data class Success(val products: List<Product>) : ProductsState()
    data object Loading : ProductsState()
    data object Error : ProductsState()
}