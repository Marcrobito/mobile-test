package com.superformula.mobiletest.util

import javax.inject.Qualifier

/**
 * Qualifier annotation used to distinguish the [CoroutineDispatcher] designated for IO-bound operations.
 *
 * Apply this annotation to provide or inject a dispatcher intended for tasks such as network or disk access.
 *
 * Usage example:
 * ```
 * @Provides
 * @IoDispatcher
 * fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
 * ```
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IoDispatcher