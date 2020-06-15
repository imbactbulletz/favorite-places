package com.example.rmaapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.rmaapp.data.local.repository.SavedLocationRepository
import com.example.rmaapp.presentation.contract.LocationInfoContract
import com.example.rmaapp.presentation.model.SavedLocation

class LocationInfoActivityViewModel(private val repository: SavedLocationRepository): ViewModel(), LocationInfoContract.ViewModel {

    override fun findById(id: Int): LiveData<SavedLocation> {
        return repository.findById(id)
    }
}