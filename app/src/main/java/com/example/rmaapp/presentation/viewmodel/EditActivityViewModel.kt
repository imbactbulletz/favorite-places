package com.example.rmaapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rmaapp.data.local.repository.SavedLocationRepository
import com.example.rmaapp.presentation.contract.EditContract
import com.example.rmaapp.presentation.model.SavedLocation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EditActivityViewModel(private val repository: SavedLocationRepository): ViewModel(), EditContract.ViewModel {

    override fun edit(savedLocation: SavedLocation) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.edit(savedLocation)
        }
    }

    override fun findById(id: Int): LiveData<SavedLocation> {
        return repository.findById(id)
    }
}