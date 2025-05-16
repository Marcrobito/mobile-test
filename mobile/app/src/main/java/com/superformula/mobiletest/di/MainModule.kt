package com.superformula.mobiletest.di

import com.superformula.mobiletest.network.NetworkService
import com.superformula.mobiletest.network.QRApi
import com.superformula.mobiletest.network.QRRepository
import com.superformula.mobiletest.network.QRRepositoryImpl
import com.superformula.mobiletest.util.IoDispatcher
import com.superformula.mobiletest.util.SystemTimeProvider
import com.superformula.mobiletest.util.TimeProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
/**
 * Provides the dependencies for network, repository, time utilities and dispatchers.
 * This module is installed in the SingletonComponent scope using Hilt.
 */
@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    /**
     * Provides the implementation of the QR API using the NetworkService.
     *
     * @return An instance of [QRApi].
     */
    @Provides
    fun providesQRApi(): QRApi = NetworkService.service

    /**
     * Provides the repository implementation that communicates with the QR API.
     *
     * @param api The QR API interface.
     * @return An instance of [QRRepository].
     */
    @Provides
    fun providesQRRepository(api: QRApi): QRRepository = QRRepositoryImpl(api)

    /**
     * Provides a time provider that returns the current system time in seconds.
     *
     * @return An instance of [TimeProvider].
     */
    @Provides
    fun providesTimeProvider(): TimeProvider = SystemTimeProvider()

    /**
     * Provides a CoroutineDispatcher annotated with [IoDispatcher] for IO operations.
     *
     * @return An instance of [CoroutineDispatcher] using [Dispatchers.IO].
     */
    @Provides
    @IoDispatcher
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}