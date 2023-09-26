package dev.duckbuddyy.shopr.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.duckbuddyy.shopr.model.ProductDetailEntity

@Dao
interface ProductDetailDao {
    /**
     * Creates or replaces information of product detail to local database.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertProductDetail(product: ProductDetailEntity)

    /**
     * Gets the information of cached product detail from local database.
     */
    @Query("SELECT * FROM product_detail WHERE productId = :productId")
    suspend fun getProductDetail(productId: String): ProductDetailEntity?
}
