package com.superformula.mobiletest.viewmodels

import com.superformula.mobiletest.entities.NetworkResponse
import com.superformula.mobiletest.entities.Seed
import com.superformula.mobiletest.network.QRRepository
import com.superformula.mobiletest.util.TimeProvider
import io.mockk.coEvery
import io.mockk.clearAllMocks
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.time.Instant
import kotlin.io.println

@OptIn(ExperimentalCoroutinesApi::class)
class SeedViewModelTest {

    private lateinit var repository: QRRepository
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    @Test
    fun `given repository returns success when getSeed is called then state is Success`() = runTest {
        val now = 1_000_000L
        val timeProvider = FakeTimeProvider(now)
        val fakeSeed = Seed("abc-123", Instant.ofEpochSecond(now + 10).toString())
        coEvery { repository.getSeed() } returns NetworkResponse.Success(fakeSeed)

        val viewModel = SeedViewModel(repository, timeProvider, testDispatcher)

        viewModel.getSeed()
        advanceTimeBy(1)
        runCurrent()

        val currentState = viewModel.state.value
        assertTrue(currentState is NetworkResponse.Success)
        assertEquals("abc-123", (currentState as NetworkResponse.Success).data.seed)
        viewModel.clearForTest()
    }

    inner class FakeTimeProvider(var currentTime: Long) : TimeProvider {
        override fun nowEpochSeconds(): Long = currentTime
    }
}