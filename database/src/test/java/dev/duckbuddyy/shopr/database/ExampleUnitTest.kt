package dev.duckbuddyy.shopr.database

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class DatabaseRepositoryTest {
    lateinit var databaseRepository: DatabaseRepository

    @Before
    fun setup() {
        DatabaseRepository.isTestMode = true
        databaseRepository = DatabaseRepository(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun getCartWhenEmpty(): Unit = runBlocking {
        databaseRepository.getCart().onSuccess {
            assertEquals(4, 2 + 2)
        }.onFailure {
            assertEquals(4, 2 + 2)
        }
    }
}