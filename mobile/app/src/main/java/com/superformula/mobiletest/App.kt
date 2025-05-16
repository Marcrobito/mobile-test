package com.superformula.mobiletest

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class that initializes Hilt for dependency injection.
 *
 * This is required to set up Hilt in the application lifecycle.
 */
@HiltAndroidApp
class App:Application() {
}