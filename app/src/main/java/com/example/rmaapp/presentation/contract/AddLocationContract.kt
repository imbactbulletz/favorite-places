package com.example.rmaapp.presentation.contract

import androidx.lifecycle.LiveData
import com.example.rmaapp.presentation.model.SavedLocation
import com.google.android.gms.maps.model.LatLng

interface AddLocationContract {
    interface ViewModel {

        fun save(savedLocation: SavedLocation)

        fun setCurrentLocationPosition(latLng: LatLng)

        fun getCurrentLocationPosition(): LiveData<LatLng>
    }
}