package com.superformula.mobiletest.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
/**
 * Displays an alert dialog informing the user that camera permission is required.
 *
 * @param showDialog Whether the dialog should be visible.
 * @param onDismiss Callback triggered when the dialog is dismissed or canceled.
 * @param onPositiveAlertButtonClicked Callback triggered when the user accepts the permission request.
 */
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