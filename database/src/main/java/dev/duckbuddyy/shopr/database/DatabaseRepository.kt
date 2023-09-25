package dev.duckbuddyy.shopr.database

import android.content.Context
import dev.duckbuddyy.shopr.model.Cart

class DatabaseRepository constructor(context: Context) {
    private val shoprDatabase: ShoprDatabase = DatabaseModule.getDatabaseInstance(context = context)

    suspend fun getCart(): Cart {
        val productEntities = shoprDatabase.productDao().getProducts()
        val products = productEntities.map { it.toProduct() }
        return Cart(products = products)
    }

    suspend fun updateCart(cart: Cart) {
        val productEntities = cart.products.map { it.toProductEntity() }
        shoprDatabase.productDao().upsertProducts(productEntities)
    }
}