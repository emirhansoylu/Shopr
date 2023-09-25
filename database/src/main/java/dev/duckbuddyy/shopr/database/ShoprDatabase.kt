package dev.duckbuddyy.shopr.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.duckbuddyy.shopr.model.ProductEntity

@Database(entities = [ProductEntity::class], version = 2)
abstract class ShoprDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {
        private var INSTANCE: ShoprDatabase? = null

        fun getDatabaseInstance(context: Context): ShoprDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShoprDatabase::class.java,
                    "shopr_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}