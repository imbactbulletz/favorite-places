package com.example.rmaapp.presentation.contract

import androidx.lifecycle.LiveData
import com.example.rmaapp.presentation.model.SavedLocation

interface LocationListContract {

    interface ViewModel {
        fun getAllSavedLocations(): LiveData<List<SavedLocation>>
    }
}