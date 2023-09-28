package dev.duckbuddyy.shopr.database

import android.content.Context
import androidx.room.Room

internal object DatabaseModule {
    fun getDatabaseInstance(
        context: Context
    ): ShoprDatabase = Room.databaseBuilder(
        context.applicationContext,
        ShoprDatabase::class.java,
        BuildConfig.DATABASE_NAME
    ).build()

    fun getTestDatabaseInstance(
        context: Context
    ): ShoprDatabase = Room.inMemoryDatabaseBuilder(
        context.applicationContext,
        ShoprDatabase::class.java,
    ).allowMainThreadQueries().build()
}