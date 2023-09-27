package dev.duckbuddyy.shopr.database

import android.content.Context
import androidx.room.Room

internal object DatabaseModule {
    private var INSTANCE: ShoprDatabase? = null
    private var TEST_INSTANCE: ShoprDatabase? = null

    fun getDatabaseInstance(context: Context): ShoprDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                ShoprDatabase::class.java,
                BuildConfig.DATABASE_NAME
            ).build()
            INSTANCE = instance
            instance
        }
    }

    fun getTestDatabaseInstance(context: Context): ShoprDatabase {
        return TEST_INSTANCE ?: synchronized(this) {
            val testInstance = Room.inMemoryDatabaseBuilder(
                context.applicationContext,
                ShoprDatabase::class.java,
            ).allowMainThreadQueries().build()
            TEST_INSTANCE = testInstance
            testInstance
        }
    }
}