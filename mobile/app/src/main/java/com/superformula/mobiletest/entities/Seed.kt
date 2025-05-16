package com.superformula.mobiletest.entities

/**
 * Represents a QR seed used for authentication or identification purposes.
 *
 * @property seed A unique string used to generate a QR code.
 * @property expiresAt The expiration timestamp of the seed in ISO-8601 format.
 */
data class Seed(
    val seed: String,
    val expiresAt: String
)
