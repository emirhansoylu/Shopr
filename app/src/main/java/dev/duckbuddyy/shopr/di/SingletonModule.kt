package dev.duckbuddyy.shopr.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.duckbuddyy.shopr.database.DatabaseRepository
import dev.duckbuddyy.shopr.domain.ShoprRepository
import dev.duckbuddyy.shopr.network.NetworkRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {

    @Singleton
    @Provides
    fun provideShoprRepository(@ApplicationContext context: Context): ShoprRepository {
        return ShoprRepository(
            network = NetworkRepository(),
            database = DatabaseRepository(context)
        )
    }
}