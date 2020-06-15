package com.example.rmaapp.presentation.contract

import androidx.lifecycle.LiveData
import com.example.rmaapp.presentation.model.SavedLocation

interface MapHistoryContract {

    interface ViewModel {

        fun findAll(): LiveData<List<SavedLocation>>

        fun setMapReady()

        fun isMapReady(): LiveData<Boolean>
    }
}