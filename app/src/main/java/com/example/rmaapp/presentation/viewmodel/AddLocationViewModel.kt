package com.example.rmaapp.presentation.viewmodel

import android.provider.Settings
import androidx.lifecycle.ViewModel
import com.example.rmaapp.data.local.repository.SavedLocationRepository
import com.example.rmaapp.presentation.contract.AddLocationContract
import com.example.rmaapp.presentation.model.SavedLocation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddLocationViewModel(private val savedLocationRepository: SavedLocationRepository): ViewModel(), AddLocationContract.ViewModel {

    override fun save(savedLocation: SavedLocation) {
        GlobalScope.launch(Dispatchers.IO) {
            savedLocationRepository.save(savedLocation)
        }
    }
}