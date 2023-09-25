package dev.duckbuddyy.shopr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import dev.duckbuddyy.shopr.database.DatabaseRepository
import dev.duckbuddyy.shopr.domain.ShoprRepository
import dev.duckbuddyy.shopr.network.NetworkRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            val repo = ShoprRepository(
                network = NetworkRepository(),
                database = DatabaseRepository(context = this@MainActivity)
            )
            repo.getCart().onSuccess { cart ->
                cart.products.forEach {
                    println(it)
                }
            }
        }
    }
}