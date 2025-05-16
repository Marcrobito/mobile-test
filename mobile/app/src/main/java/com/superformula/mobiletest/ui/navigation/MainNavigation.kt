package com.superformula.mobiletest.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.superformula.mobiletest.ui.screens.HomeScreen
import com.superformula.mobiletest.ui.screens.QRScannerScreen
import com.superformula.mobiletest.ui.screens.SeedScreen

/**
 * MainNavigation sets up the navigation graph for the application using Jetpack Compose Navigation.
 *
 * This composable manages navigation between the Home screen, QR scanner, and Seed screen.
 * It provides the necessary callbacks and routes to handle navigation actions and back presses.
 *
 * @param paddingValues Padding values applied from the parent layout (not currently used inside this graph).
 * @param onPositiveAlertButtonClicked Callback invoked when the user accepts a permission alert dialog.
 */

@Composable
fun MainNavigation(paddingValues: PaddingValues, onPositiveAlertButtonClicked: () -> Unit) {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) {
            HomeScreen(
                onScanButtonClicked = { navController.navigate(Routes.SCAN) },
                onSeedButtonClicked = { navController.navigate(Routes.GET_SEED) },
                onPositiveAlertButtonClicked = onPositiveAlertButtonClicked
            )
        }
        composable(Routes.SCAN) {
            QRScannerScreen(onBackPressed = { navController.popBackStack() })
        }
        composable(Routes.GET_SEED) {
            SeedScreen(onBackPressed = { navController.popBackStack() })
        }
    }
}