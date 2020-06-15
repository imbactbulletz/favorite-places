package com.example.rmaapp.presentation.contract

import com.example.rmaapp.presentation.model.SavedLocation

interface EditContract {

    interface ViewModel {
        fun edit(savedLocation: SavedLocation)
    }
}