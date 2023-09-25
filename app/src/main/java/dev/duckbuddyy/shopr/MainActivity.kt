package dev.duckbuddyy.shopr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import dev.duckbuddyy.shopr.database.ShoprDatabase
import dev.duckbuddyy.shopr.model.ProductEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var db: ShoprDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            db.productDao().upsertProducts(
                listOf(
                    ProductEntity(
                        productId = "1",
                        name = "Apples",
                        price = 120,
                        image = "https://s3-eu-west-1.amazonaws.com/developer-application-test/images/1.jpg"
                    ),ProductEntity(
                        productId = "2",
                        name = "Oranges",
                        price = 167,
                        image = "https://s3-eu-west-1.amazonaws.com/developer-application-test/images/1.jpg"
                    ),
                )
            )

            delay(1000)
            println("deneme")
            db.productDao().getProducts().forEach {
                println(it)
            }
        }
    }
}