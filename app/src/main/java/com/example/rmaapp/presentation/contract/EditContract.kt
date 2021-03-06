package com.example.rmaapp.presentation.contract

import androidx.lifecycle.LiveData
import com.example.rmaapp.presentation.model.SavedLocation

interface EditContract {

    interface ViewModel {

        fun findById(id: Int): LiveData<SavedLocation>

        fun edit(savedLocation: SavedLocation)
    }
}