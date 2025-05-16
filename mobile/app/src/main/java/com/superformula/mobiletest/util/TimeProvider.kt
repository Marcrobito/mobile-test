package com.superformula.mobiletest.util

import java.time.Instant

/**
 * Abstraction for providing the current time in epoch seconds.
 *
 * Useful for decoupling time-dependent logic from system clock access,
 * particularly in testing scenarios where deterministic behavior is required.
 */
interface TimeProvider {
    /**
     * Returns the current time in seconds since the Unix epoch.
     */
    fun nowEpochSeconds(): Long
}

class SystemTimeProvider : TimeProvider {
    /**
     * Default implementation of [TimeProvider] that returns the system's current epoch time.
     */
    override fun nowEpochSeconds(): Long = Instant.now().epochSecond
}