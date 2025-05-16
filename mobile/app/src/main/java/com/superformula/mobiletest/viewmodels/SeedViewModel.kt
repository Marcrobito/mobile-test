package com.superformula.mobiletest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.superformula.mobiletest.entities.NetworkResponse
import com.superformula.mobiletest.entities.NetworkResponse.IsLoading
import com.superformula.mobiletest.entities.NetworkResponse.NotInitialized
import com.superformula.mobiletest.entities.NetworkResponse.Success
import com.superformula.mobiletest.entities.Seed
import com.superformula.mobiletest.network.QRRepository
import com.superformula.mobiletest.util.IoDispatcher
import com.superformula.mobiletest.util.TimeProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.Instant
import javax.inject.Inject

/**
 * Type alias representing the response state for a Seed object.
 * It simplifies usage of NetworkResponse<Seed> throughout the ViewModel.
 */
private typealias SeedResponse = NetworkResponse<Seed>
/**
 * ViewModel responsible for fetching and holding the current seed value,
 * including a countdown timer indicating how long the seed remains valid.
 *
 * It exposes the seed state and remaining expiration time to the UI layer.
 *
 * @param repository Repository used to fetch the seed from the backend.
 * @param timeProvider Provides the current system time (injected for testability).
 * @param ioDispatcher Dispatcher used for executing IO-bound tasks like network requests.
 */
@HiltViewModel
class SeedViewModel @Inject constructor(
    private val repository: QRRepository,
    private val timeProvider: TimeProvider,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    /**
     * State representing the current result of the seed request.
     */
    private val _state = MutableStateFlow<SeedResponse>(NotInitialized)

    /**
     * Publicly exposed state of the seed result.
     */
    val state: StateFlow<SeedResponse> get() = _state

    /**
     * Remaining seconds until the seed expires.
     */
    private val _expiration = MutableStateFlow<Int?>(null)

    /**
     * Publicly exposed expiration time.
     */
    val expiration: StateFlow<Int?> get() = _expiration

    private var timerJob: Job? = null

    /**
     * Initiates a request to retrieve a new seed and starts the expiration timer if successful.
     */
    fun getSeed() {
        _state.value = IsLoading
        viewModelScope.launch {
            val result = withContext(ioDispatcher) {
                repository.getSeed()
            }

            _state.value = result

            if (result is Success) {
                startExpirationTimer(result.data.expiresAt)
            }
        }
    }

    /**
     * Starts a coroutine that updates the expiration timer every second
     * until the seed is considered expired.
     *
     * @param expiresAt ISO-8601 formatted timestamp of the expiration moment.
     */
    private fun startExpirationTimer(expiresAt: String) {
        timerJob?.cancel()
        val targetTime = Instant.parse(expiresAt).epochSecond

        timerJob = viewModelScope.launch {
            var remaining = (targetTime - timeProvider.nowEpochSeconds()).toInt()

            while (isActive && remaining > 0) {
                _expiration.value = remaining
                delay(1000)
                remaining = (targetTime - timeProvider.nowEpochSeconds()).toInt()
            }

            _expiration.value = 0
        }
    }

    /**
     * Cancels the expiration timer coroutine when the ViewModel is cleared.
     */
    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }

    /**
     * Used for testing to manually trigger the ViewModel cleanup.
     */
    fun clearForTest() = onCleared()
}