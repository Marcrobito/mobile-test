package com.superformula.mobiletest.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.superformula.mobiletest.ui.components.HomeButton
import com.superformula.mobiletest.ui.components.PermissionRequiredAlertDialog
import com.superformula.mobiletest.util.isCameraPermissionGranted

@Composable
fun HomeScreen(
    onScanButtonClicked: () -> Unit = {},
    onSeedButtonClicked: () -> Unit = {},
    onPositiveAlertButtonClicked: () -> Unit = {}
) {
    val context = LocalContext.current
    val showPermissionDialog = remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        HomeButton("Scan Qr") {
            if (context.isCameraPermissionGranted()) {
                onScanButtonClicked()
                return@HomeButton
            }
            showPermissionDialog.value = !context.isCameraPermissionGranted()
        }
        Spacer(modifier = Modifier.height(16.dp))
        HomeButton("Get Seed") {
            onSeedButtonClicked()
        }
    }

    PermissionRequiredAlertDialog(
        showDialog = showPermissionDialog.value,
        onDismiss = { showPermissionDialog.value = false },
        onPositiveAlertButtonClicked = {
            showPermissionDialog.value = false
            onPositiveAlertButtonClicked()
        }
    )

}