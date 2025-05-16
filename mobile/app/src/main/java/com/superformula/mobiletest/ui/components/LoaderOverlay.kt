package com.superformula.mobiletest.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

/**
 * Displays a full-screen loading overlay with a translucent black background.
 *
 * @param isVisible Determines whether the overlay is shown.
 */
@Composable
fun LoaderOverlay(isVisible: Boolean) {
    if (!isVisible) return

    Box(
        modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.6F)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Loading...",
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}