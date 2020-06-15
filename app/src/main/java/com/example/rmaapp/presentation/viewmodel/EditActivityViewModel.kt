package com.example.rmaapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.rmaapp.data.local.repository.SavedLocationRepository
import com.example.rmaapp.presentation.contract.EditContract
import com.example.rmaapp.presentation.model.SavedLocation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EditActivityViewModel(private val repository: SavedLocationRepository): ViewModel(), EditContract.ViewModel {

    override fun edit(savedLocation: SavedLocation) {
        GlobalScope.launch(Dispatchers.IO) {
            repository.edit(savedLocation)
        }
    }
}