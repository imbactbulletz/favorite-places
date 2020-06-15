package com.example.rmaapp.presentation.contract

import androidx.lifecycle.LiveData
import com.example.rmaapp.presentation.model.SavedLocation

interface LocationInfoContract {

    interface ViewModel {
        fun findById(id: Int): LiveData<SavedLocation>
    }
}