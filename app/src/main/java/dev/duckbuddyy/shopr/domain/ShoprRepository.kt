package dev.duckbuddyy.shopr.domain

import dev.duckbuddyy.shopr.database.DatabaseRepository
import dev.duckbuddyy.shopr.model.Cart
import dev.duckbuddyy.shopr.model.ProductDetail
import dev.duckbuddyy.shopr.network.NetworkRepository

class ShoprRepository(
    private val network: NetworkRepository,
    private val database: DatabaseRepository
) {

    suspend fun getCart(
        useCache: Boolean = true
    ) = runCatching<Cart> {
        if (useCache) {
            val cachedCart = database.getCart()
            if (cachedCart.products.isNotEmpty()) {
                return@runCatching cachedCart
            }
        }

        val networkCart = network.getCart()
        if (networkCart.products.isNotEmpty()) {
            database.updateCart(networkCart)
            return@runCatching networkCart
        }

        throw Exception()
    }

    suspend fun getProductDetail(
        productId: String,
        useCache: Boolean = true
    ) = runCatching<ProductDetail> {
        if (useCache) {
            val cachedProductDetail = database.getProductDetail(productId)
            if (cachedProductDetail != null) {
                return@runCatching cachedProductDetail
            }
        }

        val networkProductDetail = network.getProductDetail(productId)
        database.updateProductDetail(networkProductDetail)
        networkProductDetail
    }
}