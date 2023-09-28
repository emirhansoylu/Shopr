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
    ): Cart? {
        if (useCache) {
            database.getCart().onSuccess { cachedCart: Cart ->
                if (cachedCart.products.isNotEmpty()) {
                    return cachedCart
                }
            }.onFailure { it.log() }
        }

        network.getCart().onSuccess { networkCart: Cart ->
            if (networkCart.products.isNotEmpty()) {
                database.updateCart(networkCart)
                return networkCart
            }
        }.onFailure { it.log() }

        return null
    }

    suspend fun getProductDetail(
        productId: String,
        useCache: Boolean = true
    ): ProductDetail? {
        if (useCache) {
            database.getProductDetail(productId).onSuccess { cachedProductDetail: ProductDetail ->
                return cachedProductDetail
            }.onFailure { it.log() }
        }

        network.getProductDetail(productId).onSuccess { networkProductDetail: ProductDetail ->
            database.updateProductDetail(networkProductDetail)
            return networkProductDetail
        }.onFailure { it.log() }

        return null
    }
}