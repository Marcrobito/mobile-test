package com.superformula.mobiletest.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color

/**
 * A composable overlay used to simulate a scanner mask with a transparent cutout area.
 *
 * This overlay darkens the entire screen except for a rounded rectangular area in the center,
 * which acts as a cutout for scanning (e.g., QR codes). The cutout is created by drawing a
 * transparent rounded rectangle with a blend mode that clears the specified area.
 *
 * @param modifier Modifier to be applied to the Canvas.
 * @param cornerRadius The corner radius for the cutout rectangle.
 * @param cutoutWidthRatio The width of the cutout relative to the screen width (0.0 to 1.0).
 * @param cutoutHeightRatio The height of the cutout relative to the screen height (0.0 to 1.0).
 */
@Composable
fun ScannerOverlay(
    modifier: Modifier = Modifier,
    cornerRadius: Float = 24f,
    cutoutWidthRatio: Float = 0.7f,
    cutoutHeightRatio: Float = 0.35f
) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        val cutoutWidth = canvasWidth * cutoutWidthRatio
        val cutoutHeight = canvasHeight * cutoutHeightRatio

        val cutoutLeft = (canvasWidth - cutoutWidth) / 2f
        val cutoutTop = (canvasHeight - cutoutHeight) / 2f

        drawRect(
            color = Color.Black.copy(alpha = 0.6f)
        )

        drawRoundRect(
            color = Color.Transparent,
            topLeft = androidx.compose.ui.geometry.Offset(cutoutLeft, cutoutTop),
            size = androidx.compose.ui.geometry.Size(cutoutWidth, cutoutHeight),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(cornerRadius, cornerRadius),
            blendMode = BlendMode.Clear
        )
    }
}