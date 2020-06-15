package com.example.rmaapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rmaapp.data.local.repository.SavedLocationRepository
import com.example.rmaapp.presentation.contract.AddLocationContract
import com.example.rmaapp.presentation.model.SavedLocation
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddLocationViewModel(private val savedLocationRepository: SavedLocationRepository) :
    ViewModel(), AddLocationContract.ViewModel {

    private val currentPosition = MutableLiveData<LatLng>()

    override fun save(savedLocation: SavedLocation) {
        GlobalScope.launch(Dispatchers.IO) {
            savedLocationRepository.save(savedLocation)
        }
    }

    override fun setCurrentLocationPosition(latLng: LatLng) {
        currentPosition.value = latLng
    }

    override fun getCurrentLocationPosition(): LiveData<LatLng> {
        return currentPosition
    }
}