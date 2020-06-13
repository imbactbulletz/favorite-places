package com.example.rmaapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AddLocationActivity : AppCompatActivity(R.layout.activity_add_location) {

    companion object {
        private const val LOCATION_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= 23) checkAndRequestLocationPermission()
    }

    private fun checkAndRequestLocationPermission() {
        val isLocationPermissionGranted =
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)

        when(isLocationPermissionGranted) {
            PackageManager.PERMISSION_GRANTED -> { }
            PackageManager.PERMISSION_DENIED -> requestLocationPermission()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            LOCATION_REQUEST_CODE -> {
                when(grantResults[0]) {
                    PackageManager.PERMISSION_GRANTED -> {
                        // TODO request location
                    }
                    PackageManager.PERMISSION_DENIED -> {
                        when(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                            true -> showRationaleDialog()
                            false -> showFeatureNotAvailableDialog()
                        }
                    }
                }
            }
        }
    }

    private fun showRationaleDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Location permission necessary")
            .setMessage("In order to use this feature Location permission must be granted.")
            .setNegativeButton("Don't grant") { _, _ -> finish() }
            .setPositiveButton("Grant permission") { _, _ -> requestLocationPermission() }
            .show()
    }

    private fun showFeatureNotAvailableDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Feature not available")
            .setMessage("In order to use this feature you must go to Settings and manually enable Location permission for this application.")
            .setPositiveButton("Okay") { _, _ -> finish() }
            .show()
    }


    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_REQUEST_CODE
        )
    }
}