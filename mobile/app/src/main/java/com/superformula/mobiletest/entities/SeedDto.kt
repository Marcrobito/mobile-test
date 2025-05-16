package com.superformula.mobiletest.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Data Transfer Object (DTO) representing the seed information returned by the API.
 *
 * @property seed The unique identifier used to generate a QR code.
 * @property expiresAt The expiration timestamp of the seed in ISO-8601 format.
 */
@JsonClass(generateAdapter = true)
data class SeedDto(
    @Json(name = "seed") val seed: String,
    @Json(name = "expires_at") val expiresAt: String
)