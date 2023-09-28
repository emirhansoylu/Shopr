package dev.duckbuddyy.shopr.network

import dev.duckbuddyy.shopr.model.Cart
import dev.duckbuddyy.shopr.model.ProductDetail
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Test

class NetworkRepositoryTest {
    private lateinit var networkRepository: NetworkRepository

    @Before
    fun setup() {
        val mockEngine = MockEngine { request ->
            respond(
                content = when (request.url.toString()) {
                    "${BuildConfig.BASE_URL}/cart/list" -> ByteReadChannel(MockData.mockCartResponse)
                    "${BuildConfig.BASE_URL}/cart/1/detail" -> ByteReadChannel(MockData.mockProductDetailResponse)
                    else -> ByteReadChannel(""" {} """)
                },
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        networkRepository = NetworkRepository()
        networkRepository.client = HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    @Test
    fun getCartFromNetwork(): Unit = runBlocking {
        networkRepository.getCart().onSuccess { networkCart: Cart ->
            assert(networkCart == MockData.mockCart) {
                "Network cart must be same as mock cart."
            }
        }.onFailure {
            assert(false) {
                "Network request must be succeeded."
            }
        }
    }

    @Test
    fun getProductDetailFromNetwork(): Unit = runBlocking {
        networkRepository.getProductDetail(
            productId = MockData.mockProductDetail.productId
        ).onSuccess { networkProductDetail: ProductDetail ->
            assert(networkProductDetail == MockData.mockProductDetail) {
                "Network product detail must be same as mock product detail."
            }
        }.onFailure {
            assert(false) {
                "Network request must be succeeded."
            }
        }
    }
}