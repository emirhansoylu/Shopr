package dev.duckbuddyy.shopr.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.duckbuddyy.shopr.model.ProductEntity

@Database(entities = [ProductEntity::class], version = 2)
abstract class ShoprDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}