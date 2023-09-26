package dev.duckbuddyy.shopr.database

import android.content.Context
import dev.duckbuddyy.shopr.model.Cart
import dev.duckbuddyy.shopr.model.ProductDetail

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

    suspend fun getProductDetail(productId: String): ProductDetail? {
        val productDetailEntity = shoprDatabase.productDetailDao().getProductDetail(productId)
        return productDetailEntity?.toProductDetail()
    }

    suspend fun updateProductDetail(productDetail: ProductDetail) {
        val productDetailEntity = productDetail.toProductDetailEntity()
        shoprDatabase.productDetailDao().upsertProductDetail(productDetailEntity)
    }
}