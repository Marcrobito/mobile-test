package com.superformula.mobiletest.entities

import org.junit.Test
import kotlin.test.assertEquals

class MapperTest {

    @Test
    fun `given SeedDto when toSeed is called then returns equivalent Seed`() {
        val dto = SeedDto(seed = "abc-123", expiresAt = "2025-12-31T23:59:59Z")
        val expected = Seed(seed = "abc-123", expiresAt = "2025-12-31T23:59:59Z")

        val result = dto.toSeed()

        assertEquals(expected, result)
    }
}