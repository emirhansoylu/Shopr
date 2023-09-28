package dev.duckbuddyy.shopr.database

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import dev.duckbuddyy.shopr.model.Cart
import dev.duckbuddyy.shopr.model.ProductDetail
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4::class)
@SmallTest
class DatabaseRepositoryTest {
    private lateinit var databaseRepository: DatabaseRepository

    @Before
    fun setup() {
        DatabaseRepository.isTestMode = true
        databaseRepository = DatabaseRepository(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun getCartWhenEmpty(): Unit = runBlocking {
        databaseRepository.getCart().onSuccess {
            assert(false) {
                """
                    The cart should be empty when database is empty.
                """.trimIndent()
            }
        }
    }

    @Test
    fun updateCartWhenEmpty(): Unit = runBlocking {
        databaseRepository.updateCart(
            cart = MockData.mockCart
        ).onFailure {
            assert(false) {
                """
                    An error occurred while inserting cart into database.
                """.trimIndent()
            }
        }
    }

    @Test
    fun getCartWhenHasData(): Unit = runBlocking {
        databaseRepository.updateCart(
            cart = MockData.mockCart
        ).onFailure {
            assert(false) {
                """
                    An error occurred while updating the Cart.
                """.trimIndent()
            }
        }

        databaseRepository.getCart().onSuccess { cart: Cart ->
            assert(cart.products == MockData.mockCart.products) {
                """
                        Cart products are not same.
                    """.trimIndent()
            }
        }.onFailure {
            assert(false) {
                """
                    Data must be received from local database.
                """.trimIndent()
            }
        }
    }

    @Test
    fun updateCartWhenHasData(): Unit = runBlocking {
        databaseRepository.updateCart(
            cart = MockData.mockCart
        ).onFailure {
            assert(false) {
                """
                    An error occurred while inserting cart into database.
                """.trimIndent()
            }
        }

        databaseRepository.updateCart(
            cart = MockData.mockCart
        ).onFailure {
            assert(false) {
                """
                    An error occurred while inserting cart into database.
                """.trimIndent()
            }
        }
    }

    @Test
    fun getProductDetailWhenEmpty(): Unit = runBlocking {
        databaseRepository.getProductDetail(
            productId = MockData.mockProductDetail.productId
        ).onSuccess {
            assert(false) {
                """
                    The cart detail should be null when database is empty.
                """.trimIndent()
            }
        }
    }

    @Test
    fun updateProductDetailWhenEmpty(): Unit = runBlocking {
        databaseRepository.updateProductDetail(
            productDetail = MockData.mockProductDetail
        ).onFailure {
            assert(false) {
                """
                    An error occurred while inserting product detail into database.
                """.trimIndent()
            }
        }
    }

    @Test
    fun getProductDetailWhenHasData(): Unit = runBlocking {
        databaseRepository.updateProductDetail(
            productDetail = MockData.mockProductDetail
        ).onFailure {
            assert(false) {
                """
                    An error occurred while updating the Cart.
                """.trimIndent()
            }
        }

        databaseRepository.getProductDetail(
            productId = MockData.mockProductDetail.productId
        ).onSuccess { productDetail: ProductDetail ->
            assert(productDetail == MockData.mockProductDetail) {
                """
                        Product details are not same.
                    """.trimIndent()
            }
        }.onFailure {
            assert(false) {
                """
                    Data must be received from local database.
                """.trimIndent()
            }
        }
    }

    @Test
    fun updateProductDetailWhenHasData(): Unit = runBlocking {
        databaseRepository.updateProductDetail(
            productDetail = MockData.mockProductDetail
        ).onFailure {
            assert(false) {
                """
                    An error occurred while inserting product detail into database.
                """.trimIndent()
            }
        }
        databaseRepository.updateProductDetail(
            productDetail = MockData.mockProductDetail
        ).onFailure {
            assert(false) {
                """
                    An error occurred while inserting product detail into database.
                """.trimIndent()
            }
        }
    }
}