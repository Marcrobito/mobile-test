package com.superformula.mobiletest.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color

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