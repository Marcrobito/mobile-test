package com.superformula.mobiletest.util

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.core.content.ContextCompat
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import androidx.core.graphics.set
import androidx.core.graphics.createBitmap

/**
 * Checks if the camera permission has been granted in the current [Context].
 *
 * @return `true` if the camera permission is granted, `false` otherwise.
 */
fun Context.isCameraPermissionGranted() = ContextCompat.checkSelfPermission(
    this,
    android.Manifest.permission.CAMERA
) == PackageManager.PERMISSION_GRANTED

/**
 * Generates a QR code [Bitmap] from the given [String].
 *
 * This function uses ZXing to encode the string into a QR code image.
 * If generation fails (e.g., due to encoding issues), `null` is returned.
 *
 * @param size The width and height of the generated QR code bitmap in pixels. Default is 512.
 * @return A [Bitmap] representing the QR code, or `null` if an error occurs.
 */
fun String.generateQR(size: Int = 512) : Bitmap? {
    return try {
        val hints = mapOf(EncodeHintType.CHARACTER_SET to "UTF-8")
        val bitMatrix = QRCodeWriter().encode(this, BarcodeFormat.QR_CODE, size, size, hints)
        val bmp = createBitmap(size, size, Bitmap.Config.RGB_565)

        for (x in 0 until size) {
            for (y in 0 until size) {
                bmp[x, y] =
                    if (bitMatrix[x, y]) android.graphics.Color.BLACK else android.graphics.Color.WHITE
            }
        }
        bmp
    } catch (e: WriterException) {
        e.printStackTrace()
        null
    }
}