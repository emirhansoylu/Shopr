package dev.duckbuddyy.shopr.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    @SerialName("image")
    val image: String,
    @SerialName("name")
    val name: String,
    @SerialName("price")
    val price: Int,
    @SerialName("product_id")
    val productId: String
) {
    fun toProductEntity() = ProductEntity(
        image = image,
        name = name,
        price = price,
        productId = productId
    )
}