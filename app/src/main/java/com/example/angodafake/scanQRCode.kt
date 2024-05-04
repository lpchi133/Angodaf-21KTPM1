package com.example.angodafake

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.CodeScannerView
import android.Manifest
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode

class ScanQRCodeActivity : AppCompatActivity() {
    private lateinit var scanQRCode: CodeScanner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_qrcode)

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 123)
        } else {
            starScanning()
        }
    }

    private fun starScanning() {
        val scannerView: CodeScannerView = findViewById(R.id.codeScannerView)
        scanQRCode = CodeScanner(this, scannerView)
        scanQRCode.camera = CodeScanner.CAMERA_BACK
        scanQRCode.formats = CodeScanner.ALL_FORMATS
        scanQRCode.autoFocusMode = AutoFocusMode.SAFE
        scanQRCode.scanMode = ScanMode.SINGLE
        scanQRCode.isAutoFocusEnabled = true
        scanQRCode.isFlashEnabled = false
        scanQRCode.decodeCallback = DecodeCallback {
            runOnUiThread{
                println("Scanner result: ${it.text}")
            }
        }
        scanQRCode.errorCallback = ErrorCallback {
            runOnUiThread {
                println("error")
            }
        }
        scannerView.setOnClickListener{
            scanQRCode.startPreview()
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 123){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                starScanning()
            } else {

            }
        }
    }
    override fun onResume() {
        super.onResume()
        if (::scanQRCode.isInitialized){
            scanQRCode.startPreview()
        }
    }
    override fun onPause() {
        if (::scanQRCode.isInitialized){
            scanQRCode.releaseResources()
        }
        super.onPause()
    }
}