package com.superformula.mobiletest.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun PermissionRequiredAlertDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onPositiveAlertButtonClicked: () -> Unit
) {
    if (!showDialog) return

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Camera Permission Required") },
        text = { Text("We need access to your camera to scan QR codes. Please grant permission to continue.") },
        confirmButton = {
            TextButton(onClick = {
                onDismiss()
                onPositiveAlertButtonClicked()
            }) {
                Text("Accept")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}