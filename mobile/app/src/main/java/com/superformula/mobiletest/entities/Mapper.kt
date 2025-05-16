package com.superformula.mobiletest.entities

/**
 * Maps a [SeedDto] to a [Seed] entity.
 *
 * @return A [Seed] object containing the same `seed` and `expiresAt` values from the DTO.
 */
fun SeedDto.toSeed() = Seed(seed, expiresAt)