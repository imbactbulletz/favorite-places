package com.example.rmaapp.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.rmaapp.presentation.contract.MapHistoryContract
import com.example.rmaapp.presentation.viewmodel.MapHistoryFragmentViewModel
import com.google.android.gms.maps.SupportMapFragment
import org.koin.android.viewmodel.ext.android.viewModel
import androidx.lifecycle.observe
import com.example.rmaapp.presentation.model.SavedLocation
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.maps.android.clustering.ClusterManager

class MapHistoryFragment: SupportMapFragment() {

    private val viewModel: MapHistoryContract.ViewModel by viewModel<MapHistoryFragmentViewModel>()

    private lateinit var map: GoogleMap

    private lateinit var clusterManager: ClusterManager<SavedLocation>

    private val onMapReadyCallback = OnMapReadyCallback { googleMap ->
        map = googleMap
        viewModel.setMapReady()

        clusterManager = ClusterManager(activity, map)
        map.setOnCameraIdleListener(clusterManager)
    }

    override fun onCreate(p0: Bundle?) {
        super.onCreate(p0)
        initMap()
    }

    private fun initMap() {
        getMapAsync(onMapReadyCallback)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.findAll().observe(viewLifecycleOwner) { savedLocations ->
            viewModel.isMapReady().observe(viewLifecycleOwner) { isMapReady ->
                if (isMapReady) {
                    clusterManager.addItems(savedLocations)
                }
            }
        }
    }

}