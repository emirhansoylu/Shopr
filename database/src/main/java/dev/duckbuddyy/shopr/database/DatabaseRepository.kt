package dev.duckbuddyy.shopr.database

import android.content.Context
import dev.duckbuddyy.shopr.model.Cart
import dev.duckbuddyy.shopr.model.ProductDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DatabaseRepository constructor(context: Context) {
    private val shoprDatabase: ShoprDatabase by lazy {
        if (isTestMode)
            DatabaseModule.getTestDatabaseInstance(context = context)
        else
            DatabaseModule.getDatabaseInstance(context = context)
    }

    /**
     * Gets the cached product list from cache.
     */
    suspend fun getCart(): Result<Cart> = withContext(Dispatchers.IO) {
        runCatching {
            val productEntities = shoprDatabase.productDao().getProducts()
            if(productEntities.isEmpty()){
                throw Exception("Cart should not be empty")
            }
            val products = productEntities.map { it.toProduct() }
            Cart(products = products)
        }
    }

    /**
     * Updates the cached product list.
     * @param cart The new product list that will be cached
     */
    suspend fun updateCart(
        cart: Cart
    ): Result<Unit> = withContext(Dispatchers.IO) {
        runCatching {
            val productEntities = cart.products.map { it.toProductEntity() }
            shoprDatabase.productDao().upsertProducts(productEntities)
        }
    }

    /**
     * Gets the cached product detail from cache.
     * @param productId Identifier of the product detail object, that retrieved from cache.
     */
    suspend fun getProductDetail(
        productId: String
    ): Result<ProductDetail?> = withContext(Dispatchers.IO) {
        runCatching {
            val productDetailEntity = shoprDatabase.productDetailDao().getProductDetail(productId)
            productDetailEntity?.toProductDetail()
        }
    }

    /**
     * Updates the cached product detail.
     * @param productDetail The new product detail object that will be cached
     */
    suspend fun updateProductDetail(
        productDetail: ProductDetail
    ): Result<Unit> = withContext(Dispatchers.IO) {
        runCatching {
            val productDetailEntity = productDetail.toProductDetailEntity()
            shoprDatabase.productDetailDao().upsertProductDetail(productDetailEntity)
        }
    }

    internal companion object {
        var isTestMode = false
    }
}