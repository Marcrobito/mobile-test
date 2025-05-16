package com.superformula.mobiletest.entities

/**
 * Represents the state of a network operation.
 *
 * This sealed class encapsulates the possible outcomes of a network request.
 *
 * @param T The type of data expected in case of a successful response.
 */
sealed class NetworkResponse<out T> {

    /**
     * Indicates that the request has not yet been initialized.
     */
    data object NotInitialized : NetworkResponse<Nothing>()

    /**
     * Indicates that the request is currently in progress.
     */
    data object IsLoading : NetworkResponse<Nothing>()

    /**
     * Represents a successful network response.
     *
     * @param data The data returned from the request.
     */
    data class Success<T>(val data: T) : NetworkResponse<T>()

    /**
     * Represents a failed network response.
     *
     * @param error A message describing the error that occurred.
     */
    data class Error(val error: String) : NetworkResponse<Nothing>()
}