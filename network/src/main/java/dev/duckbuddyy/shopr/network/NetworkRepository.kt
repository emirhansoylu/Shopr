package dev.duckbuddyy.shopr.network

import dev.duckbuddyy.shopr.model.Cart
import dev.duckbuddyy.shopr.model.ProductDetail
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class NetworkRepository {
    private val client: HttpClient = NetworkModule.httpClient

    suspend fun getCart(): Cart? = try {
        val url = BuildConfig.BASE_URL + "/cart/list"
        client.get(url).body()
    } catch (e: Exception) {
        null
    }

    suspend fun getProductDetail(productId: String): ProductDetail? = try {
        val url = BuildConfig.BASE_URL + "/${productId}/detail"
        client.get(url).body()
    } catch (e: java.lang.Exception) {
        null
    }
}