package com.example.rmaapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.rmaapp.data.local.repository.SavedLocationRepository
import com.example.rmaapp.presentation.contract.MapHistoryContract
import com.example.rmaapp.presentation.model.SavedLocation

class MapHistoryFragmentViewModel(private val repository: SavedLocationRepository): ViewModel(), MapHistoryContract.ViewModel {

    override fun findAll(): LiveData<List<SavedLocation>> {
        return repository.findAll()
    }

}