package dev.duckbuddyy.shopr.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable
data class ProductDetail(
    @JsonNames("description","decription")
    val description: String,
    @SerialName("image")
    val image: String,
    @SerialName("name")
    val name: String,
    @SerialName("price")
    val price: Int,
    @SerialName("product_id")
    val productId: String
) {
    fun toProductDetailEntity() = ProductDetailEntity(
        image = image,
        name = name,
        price = price,
        productId = productId,
        description = description
    )
}