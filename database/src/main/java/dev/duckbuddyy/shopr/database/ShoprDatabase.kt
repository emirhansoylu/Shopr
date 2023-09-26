package dev.duckbuddyy.shopr.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.duckbuddyy.shopr.model.ProductDetailEntity
import dev.duckbuddyy.shopr.model.ProductEntity

@Database(
    entities = [ProductEntity::class, ProductDetailEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ShoprDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun productDetailDao(): ProductDetailDao
}