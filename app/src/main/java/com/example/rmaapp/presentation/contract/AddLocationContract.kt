package com.example.rmaapp.presentation.contract

import androidx.lifecycle.LiveData
import com.example.rmaapp.presentation.model.SavedLocation

interface AddLocationContract {
    interface ViewModel {

        fun save(savedLocation: SavedLocation)
    }
}