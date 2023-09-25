package dev.duckbuddyy.shopr.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.duckbuddyy.shopr.R
import dev.duckbuddyy.shopr.database.ShoprDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {

    @Singleton
    @Provides
    fun provideShoprDatabase(@ApplicationContext context: Context): ShoprDatabase {
        val databaseName = context.getString(R.string.app_name)
        return Room.databaseBuilder(
            context,
            ShoprDatabase::class.java,
            databaseName
        ).build()
    }
}