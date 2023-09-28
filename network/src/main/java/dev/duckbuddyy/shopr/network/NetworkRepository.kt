package dev.duckbuddyy.shopr.network

import dev.duckbuddyy.shopr.model.Cart
import dev.duckbuddyy.shopr.model.ProductDetail
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkRepository {
    internal var client: HttpClient = NetworkModule.httpClient

    /**
     * Gets the product list from network.
     */
    suspend fun getCart(): Result<Cart> = withContext(Dispatchers.IO) {
        runCatching {
            val url = BuildConfig.BASE_URL + "/cart/list"
            client.get(url).body()
        }
    }

    /**
     * Gets the product detail from network.
     * @param productId Identifier of the product detail that that retrieved from network.
     */
    suspend fun getProductDetail(
        productId: String
    ): Result<ProductDetail> = withContext(Dispatchers.IO) {
        runCatching {
            val url = BuildConfig.BASE_URL + "/cart/${productId}/detail"
            client.get(url).body()
        }
    }
}