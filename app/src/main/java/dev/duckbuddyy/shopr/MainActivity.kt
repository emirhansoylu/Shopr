package dev.duckbuddyy.shopr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import dev.duckbuddyy.shopr.database.ShoprDatabase
import dev.duckbuddyy.shopr.network.ShoprNetwork
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var db: ShoprDatabase
    @Inject
    lateinit var network: ShoprNetwork

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            network.getCart().products.forEach {
                println("network" + it)
            }

            delay(1000)

            db.productDao().getProducts().forEach {
                println("cache" + it)
            }
        }
    }
}