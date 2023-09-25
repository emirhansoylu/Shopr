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
import dev.duckbuddyy.shopr.network.ShoprNetwork
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.cio.endpoint
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
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

    @Singleton
    @Provides
    fun provideShoprNetwork(): ShoprNetwork {
        val client = HttpClient(CIO) {
            engine {
                maxConnectionsCount = 1000
                endpoint {
                    keepAliveTime = 5000
                    connectTimeout = 5000
                    connectAttempts = 5
                }
            }
            install(Logging) {
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
        }
        return ShoprNetwork(client)
    }
}