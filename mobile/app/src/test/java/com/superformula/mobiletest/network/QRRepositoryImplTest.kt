package com.superformula.mobiletest.network

import com.superformula.mobiletest.entities.NetworkResponse
import com.superformula.mobiletest.entities.SeedDto
import com.superformula.mobiletest.entities.toSeed
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class QRRepositoryImplTest {

    private val api = mockk<QRApi>()
    private val repository = QRRepositoryImpl(api)

    @Test
    fun `given repository returns dto when getSeed is called then result is Success`() = runTest {
        val dto = SeedDto(seed = "abc-123", expiresAt = "2025-12-31T23:59:59Z")
        coEvery { api.getStories() } returns dto

        val result = repository.getSeed()

        assertTrue(result is NetworkResponse.Success)
        assertEquals("abc-123", result.data.seed)
    }

    @Test
    fun `given repository throws exception when getSeed is called then result is Error`() = runTest {
        coEvery { api.getStories() } throws RuntimeException("Something went wrong")

        val result = repository.getSeed()

        assertTrue(result is NetworkResponse.Error)
        assertEquals("Something went wrong", result.error)
    }
}