package dev.duckbuddyy.shopr.network

import dev.duckbuddyy.shopr.model.Cart
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class NetworkRepository {
    private val client: HttpClient = NetworkModule.httpClient

    suspend fun getCart(): Cart {
        val url = BuildConfig.BASE_URL + "/cart/list"
        return client.get(url).body()
    }
}