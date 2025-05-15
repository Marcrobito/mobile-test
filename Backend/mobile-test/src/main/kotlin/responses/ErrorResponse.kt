package com.superformula.responses

import kotlinx.serialization.Serializable

/**
 * Represents a standardized error response for the API.
 *
 * @property error A human-readable error message.
 * @property status The associated HTTP status code.
 */

@Serializable
data class ErrorResponse(
    val error: String,
    val status: Int
)
