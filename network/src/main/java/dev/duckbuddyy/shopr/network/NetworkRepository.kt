package dev.duckbuddyy.shopr.network

import dev.duckbuddyy.shopr.model.Cart
import dev.duckbuddyy.shopr.model.ProductDetail
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class NetworkRepository {
    private val client: HttpClient = NetworkModule.httpClient

    suspend fun getCart(): Cart {
        val url = BuildConfig.BASE_URL + "/cart/list"
        return client.get(url).body()
    }

    suspend fun getProductDetail(productId: String): ProductDetail {
        val url = BuildConfig.BASE_URL + "/cart/${productId}/detail"
        return client.get(url).body()
    }
}