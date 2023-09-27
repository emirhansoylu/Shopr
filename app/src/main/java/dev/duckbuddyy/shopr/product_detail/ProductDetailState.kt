package dev.duckbuddyy.shopr.product_detail

import dev.duckbuddyy.shopr.model.ProductDetail

sealed class ProductDetailState {
    data class Success(val productDetail: ProductDetail) : ProductDetailState()
    data object Loading : ProductDetailState()
    data object Error : ProductDetailState()
}