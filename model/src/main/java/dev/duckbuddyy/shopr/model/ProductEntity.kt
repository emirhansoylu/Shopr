package dev.duckbuddyy.shopr.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    val image: String,
    val name: String,
    val price: Int,
    @PrimaryKey
    val productId: String
) {
    fun toProduct() = Product(
        image = image,
        name = name,
        price = price,
        productId = productId
    )
}