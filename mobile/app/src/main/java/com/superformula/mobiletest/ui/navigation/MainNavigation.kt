package com.superformula.mobiletest.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.superformula.mobiletest.ui.screens.HomeScreen
import com.superformula.mobiletest.ui.screens.QRScannerScreen

@Composable
fun MainNavigation( onPositiveAlertButtonClicked:() -> Unit){
    val navController: NavHostController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) { backStackEntry ->
            HomeScreen(onScanButtonClicked = {
                navController.navigate(Routes.SCAN)
            }, onPositiveAlertButtonClicked = onPositiveAlertButtonClicked)
        }
        composable(Routes.SCAN) { backStackEntry ->
            QRScannerScreen(onBackPressed = {navController.popBackStack()}) { scannedQRCode ->
                Log.d("Scanner", scannedQRCode)
            }
        }

    }
}