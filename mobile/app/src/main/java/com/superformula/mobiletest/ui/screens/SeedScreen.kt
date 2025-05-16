package com.superformula.mobiletest.ui.screens

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.core.graphics.createBitmap
import androidx.hilt.navigation.compose.hiltViewModel
import com.superformula.mobiletest.entities.NetworkResponse
import com.superformula.mobiletest.entities.Seed
import com.superformula.mobiletest.ui.components.LoaderOverlay
import com.superformula.mobiletest.util.generateQR
import com.superformula.mobiletest.viewmodels.SeedViewModel

/**
 * Composable that displays a screen for fetching and showing a QR code ("seed").
 *
 * This screen uses a [SeedViewModel] to request a seed from the backend and display it
 * as a QR code. It also shows a countdown timer indicating how long the seed remains valid.
 * When the seed expires, the user can request a new one via a button.
 *
 * A loading overlay is shown during data fetching. A back arrow is provided to exit the screen.
 *
 * @param onBackPressed Callback invoked when the back button is pressed.
 * @param seedViewModel The ViewModel used to manage state and business logic. Defaults to Hilt-injected.
 */
@Composable
fun SeedScreen(
    onBackPressed: () -> Unit,
    seedViewModel: SeedViewModel = hiltViewModel()
) {
    val state by seedViewModel.state.collectAsState()

    var qrBitmap by remember { mutableStateOf<Bitmap?>(null) }

    var showLoaderOverlay by remember { mutableStateOf(true) }
    val expiration by seedViewModel.expiration.collectAsState()

    val displayBitmap = qrBitmap ?: createBitmap(512, 512).apply {
        eraseColor(android.graphics.Color.LTGRAY)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize().padding(vertical = 48.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text("UID", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(64.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally,) {
                Image(
                    bitmap = (qrBitmap ?: displayBitmap).asImageBitmap(),
                    contentDescription = "QR Code",
                    modifier = Modifier
                        .fillMaxWidth(0.7F)
                        .aspectRatio(1F)
                )
                Spacer(modifier = Modifier.height(16.dp))
                expiration?.let {
                    if (it > 0) {
                        Text(
                            "Expires in: $expiration seconds",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        return@let
                    }

                    Text("Expired", style = MaterialTheme.typography.bodyMedium, color = Color.Red)
                    Spacer(modifier = Modifier.height(16.dp))
                    Button({ seedViewModel.getSeed() }) {
                        Text("Request new UID")
                    }
                }
            }


        }

        IconButton(
            onClick = { onBackPressed() },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.onBackground
            )
        }

        LoaderOverlay(showLoaderOverlay)


    }
    when (state) {
        is NetworkResponse.Error -> {
            showLoaderOverlay = false
        }

        NetworkResponse.IsLoading -> showLoaderOverlay = true
        NetworkResponse.NotInitialized -> {
            showLoaderOverlay = false
            LaunchedEffect(Unit) {
                seedViewModel.getSeed()
            }
        }

        is NetworkResponse.Success<Seed> -> {
            val data = (state as NetworkResponse.Success<Seed>).data
            showLoaderOverlay = false
            qrBitmap = data.seed.generateQR()
        }
    }

}