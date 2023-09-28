package dev.duckbuddyy.shopr.database

import dev.duckbuddyy.shopr.model.Cart
import dev.duckbuddyy.shopr.model.Product
import dev.duckbuddyy.shopr.model.ProductDetail

object MockData {
    val mockCart = Cart(
        products = listOf(
            Product(
                productId = "1",
                name = "Apples",
                price = 120,
                image = "https://s3-eu-west-1.amazonaws.com/developer-application-test/images/1.jpg"
            ),
            Product(
                productId = "2",
                name = "Oranges",
                price = 167,
                image = "https://s3-eu-west-1.amazonaws.com/developer-application-test/images/2.jpg"
            ),
            Product(
                productId = "3",
                name = "Bananas",
                price = 88,
                image = "https://s3-eu-west-1.amazonaws.com/developer-application-test/images/3.jpg"
            ),
            Product(
                productId = "4",
                name = "Onions",
                price = 110,
                image = "https://s3-eu-west-1.amazonaws.com/developer-application-test/images/4.jpg"
            ),
            Product(
                productId = "5",
                name = "Steak",
                price = 543,
                image = "https://s3-eu-west-1.amazonaws.com/developer-application-test/images/5.jpg"
            ),
            Product(
                productId = "6_id_is_a_string",
                name = "Pork",
                price = 343,
                image = "https://s3-eu-west-1.amazonaws.com/developer-application-test/images/6.jpg"
            ),
            Product(
                productId = "7",
                name = "Chicken",
                price = 272,
                image = "https://s3-eu-west-1.amazonaws.com/developer-application-test/images/chicken.jpg"
            ),
            Product(
                productId = "8",
                name = "Salmon",
                price = 267,
                image = "https://s3-eu-west-1.amazonaws.com/developer-application-test/images/8.jpg"
            ),
            Product(
                productId = "9",
                name = "Tuna",
                price = 557,
                image = "https://s3-eu-west-1.amazonaws.com/developer-application-test/images/9.jpg"
            ),
            Product(
                productId = "10",
                name = "Broccoli",
                price = 32,
                image = "https://s3-eu-west-1.amazonaws.com/developer-application-test/images/10.jpg"
            ),
            Product(
                productId = "11",
                name = "Bacon",
                price = 212,
                image = "https://s3-eu-west-1.amazonaws.com/developer-application-test/images/11.jpg"
            ),
            Product(
                productId = "12",
                name = "Peppers",
                price = 9,
                image = "https://s3-eu-west-1.amazonaws.com/developer-application-test/images/12.jpg"
            )
        )
    )

    val mockProductDetail = ProductDetail(
        productId = "1",
        name = "Apples",
        price = 120,
        image = "https://s3-eu-west-1.amazonaws.com/developer-application-test/images/1.jpg",
        description = "An apple a day keeps the doctor away."
    )
}