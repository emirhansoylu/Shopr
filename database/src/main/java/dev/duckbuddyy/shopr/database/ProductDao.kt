package dev.duckbuddyy.shopr.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.duckbuddyy.shopr.model.ProductEntity

@Dao
interface ProductDao {
    /**
     * Creates or replaces products on
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertProducts(products: List<ProductEntity>)

    /**
     * Gets the
     */
    @Query("SELECT * FROM products")
    fun getProducts(): List<ProductEntity>
}
