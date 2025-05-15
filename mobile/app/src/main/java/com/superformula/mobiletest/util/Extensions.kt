package com.superformula.mobiletest.util

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

fun Context.isCameraPermissionGranted() = ContextCompat.checkSelfPermission(
    this,
    android.Manifest.permission.CAMERA
) == PackageManager.PERMISSION_GRANTED