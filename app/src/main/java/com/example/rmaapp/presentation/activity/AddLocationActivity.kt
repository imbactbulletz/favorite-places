package com.example.rmaapp.presentation.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.rmaapp.R
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AddLocationActivity : AppCompatActivity(R.layout.activity_add_location), OnMapReadyCallback {

    companion object {
        private const val LOCATION_REQUEST_CODE = 1

        private const val ZOOM_FACTOR = 17F

        private const val SYDNEY_LAT = -33.865143
        private const val SYDNEY_LON = 151.209900
    }

    private var map: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initMapsFragment()
    }

    private fun initMapsFragment() {
        (supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment)
            .getMapAsync(this)
    }


    @SuppressLint("MissingPermission")
    private fun requestLocation() {

        LocationServices.getFusedLocationProviderClient(this).lastLocation.addOnSuccessListener { location ->
            val position = if (location != null) LatLng(location.latitude, location.longitude)
                else LatLng(
                SYDNEY_LAT,
                SYDNEY_LON
            )

            val marker = MarkerOptions()
                .position(position)

            map?.clear()
            map?.addMarker(marker)
            map?.animateCamera(CameraUpdateFactory.newLatLngZoom(position,
                ZOOM_FACTOR
            ))
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
                when (grantResults[0]) {
                    PackageManager.PERMISSION_GRANTED -> requestLocation()
                    PackageManager.PERMISSION_DENIED -> {
                        when (ActivityCompat.shouldShowRequestPermissionRationale(
                            this,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        )) {
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

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map?.run {
            uiSettings.isZoomControlsEnabled = true
            uiSettings.isZoomGesturesEnabled = true
        }

        val isLocationPermissionGranted =
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)

        when (isLocationPermissionGranted) {
            PackageManager.PERMISSION_GRANTED -> requestLocation()
            PackageManager.PERMISSION_DENIED -> if(Build.VERSION.SDK_INT >= 23) requestLocationPermission()
        }
    }
}