package dev.duckbuddyy.shopr.domain

import dev.duckbuddyy.shopr.database.DatabaseRepository
import dev.duckbuddyy.shopr.model.Cart
import dev.duckbuddyy.shopr.network.NetworkRepository

class ShoprRepository(
    private val network: NetworkRepository,
    private val database: DatabaseRepository
) {

    suspend fun getCart() = runCatching<Cart> {
        val cachedCart = database.getCart()
        if (cachedCart.products.isNotEmpty()) {
            return@runCatching cachedCart
        }

        val networkCart = network.getCart()
        if (networkCart.products.isNotEmpty()) {
            database.updateCart(networkCart)
            return@runCatching networkCart
        }

        throw Exception()
    }
}