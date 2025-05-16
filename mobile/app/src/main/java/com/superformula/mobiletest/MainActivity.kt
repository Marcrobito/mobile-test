package com.superformula.mobiletest

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.superformula.mobiletest.ui.navigation.MainNavigation
import com.superformula.mobiletest.ui.theme.MobileTestTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main entry point for the app, annotated with [AndroidEntryPoint] to enable Hilt dependency injection.
 *
 * This activity sets up the theme, system UI behavior, and manages runtime camera permission requests.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /**
     * Launcher used to request camera permission from the user at runtime.
     */
    private lateinit var requestCameraPermissionLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initializes the permission launcher for requesting camera access.
        requestCameraPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                Log.d("PERMISSION", "Camera permission granted")
            } else {
                Log.d("PERMISSION", "Camera permission denied")
            }
        }

        enableEdgeToEdge()

        // Sets the Compose UI content and handles navigation and layout padding.
        setContent {
            MobileTestTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(WindowInsets.systemBars.asPaddingValues()),
                    topBar = { }
                ) { innerPadding ->
                    MainNavigation(
                        innerPadding,
                        onPositiveAlertButtonClicked = {
                            requestCameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                        }
                    )
                }
            }
        }
    }
}