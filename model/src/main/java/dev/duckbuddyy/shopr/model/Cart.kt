package dev.duckbuddyy.shopr.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Cart(
    @SerialName("products")
    val products: List<Product>
)