package com.example.campus360.ui

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DecoratedBarcodeView

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun QrScanScreen(
    nav: NavHostController,
    onResult: (String) -> Unit
) {
    val cameraPermission = rememberPermissionState(Manifest.permission.CAMERA)
    var barcodeView by remember { mutableStateOf<DecoratedBarcodeView?>(null) }

    DisposableEffect(Unit) {
        if (cameraPermission.status.isGranted) {
            barcodeView?.resume()
        }
        onDispose {
            barcodeView?.pause()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(20.dp))

        Text("Scan QR Code", style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary)

        Spacer(Modifier.height(12.dp))

        if (cameraPermission.status.isGranted) {
            AndroidView(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                factory = { ctx ->
                    DecoratedBarcodeView(ctx).apply {
                        barcodeView = this
                        decodeContinuous(object : BarcodeCallback {
                            override fun barcodeResult(result: BarcodeResult?) {
                                result?.text?.let { scannedText ->
                                    pause()
                                    onResult(scannedText)
                                    nav.popBackStack()
                                }
                            }
                        })
                        resume()
                    }
                }
            )
        } else {
            Column(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Camera permission required",
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(Modifier.height(12.dp))

                Button(
                    onClick = { cameraPermission.launchPermissionRequest() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Grant Permission")
                }
            }
        }

        Spacer(Modifier.height(12.dp))

        Button(
            onClick = {
                barcodeView?.pause()
                nav.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cancel")
        }
    }
}