package com.example.rmaapp.data.local.repository

import androidx.lifecycle.LiveData
import com.example.rmaapp.presentation.model.SavedLocation

interface SavedLocationRepository {

    fun save(savedLocation: SavedLocation)

    fun edit(savedLocation: SavedLocation)

    fun findAll(): LiveData<List<SavedLocation>>
}