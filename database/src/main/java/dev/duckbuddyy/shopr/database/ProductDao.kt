package dev.duckbuddyy.shopr.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.duckbuddyy.shopr.model.ProductEntity

@Dao
interface ProductDao {
    /**
     * Creates or replaces cached products on local database.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertProducts(products: List<ProductEntity>)

    /**
     * Gets the cached products from local database.
     */
    @Query("SELECT * FROM products")
    suspend fun getProducts(): List<ProductEntity>
}
