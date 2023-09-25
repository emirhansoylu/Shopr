package dev.duckbuddyy.shopr.network

import dev.duckbuddyy.shopr.model.Cart
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ShoprNetwork(private val client: HttpClient) {

    suspend fun getCart(): Cart {
        return client.get("").body() as Cart
    }
}