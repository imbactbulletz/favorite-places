package com.example.rmaapp.presentation.viewmodel

import androidx.lifecycle.*
import com.example.rmaapp.data.local.repository.SavedLocationRepository
import com.example.rmaapp.presentation.contract.LocationListContract
import com.example.rmaapp.presentation.model.SavedLocation

class LocationListFragmentViewModel(private val repository: SavedLocationRepository) : ViewModel(),
    LocationListContract.ViewModel {

    override fun getAllSavedLocations(): LiveData<List<SavedLocation>> {
        return repository.findAll()
    }
}