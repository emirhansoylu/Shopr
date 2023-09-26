package dev.duckbuddyy.shopr.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_detail")
data class ProductDetailEntity(
    val description: String,
    val image: String,
    val name: String,
    val price: Int,
    @PrimaryKey
    val productId: String
) {
    fun toProductDetail() = ProductDetail(
        description = description,
        image = image,
        name = name,
        price = price,
        productId = productId
    )
}