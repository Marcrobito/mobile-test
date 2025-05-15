package com.superformula.seed

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.UUID

/**
 * Represents a backend-generated seed object.
 *
 * @property seed A unique identifier generated as a UUID.
 * @property expiresAt Expiration timestamp in ISO-8601 format.
 */
@Serializable
data class Seed(
    val seed: String,

    @SerialName("expires_at")
    val expiresAt: String
) {
    companion object {
        /**
         * Generates a new [Seed] instance with a random UUID and an expiration
         * time 30 seconds from the current moment.
         *
         * @return A new [Seed] instance.
         */
        fun getNewSeed(): Seed = Seed(
            seed = UUID.randomUUID().toString(),
            expiresAt = Instant.now().plusSeconds(30).toString()
        )
    }
}