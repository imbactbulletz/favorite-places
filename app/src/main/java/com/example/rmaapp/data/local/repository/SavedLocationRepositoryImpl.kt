package com.example.rmaapp.data.local.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.example.rmaapp.data.local.datasource.database.dao.SavedLocationDao
import com.example.rmaapp.data.local.model.SavedLocationEntity
import com.example.rmaapp.presentation.model.SavedLocation
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SavedLocationRepositoryImpl(private val savedLocationDao: SavedLocationDao) :
    SavedLocationRepository {

    override fun save(savedLocation: SavedLocation) {
        savedLocationDao.save(savedLocation.toDomainModel())
    }

    override fun edit(savedLocation: SavedLocation) {
        savedLocationDao.edit(savedLocation.toDomainModel())
    }

    override fun findById(id: Int): LiveData<SavedLocation> {
        return savedLocationDao.findById(id).switchMap { MutableLiveData(it.toUiModel()) }
    }

    override fun findAll(): LiveData<List<SavedLocation>> {
        return savedLocationDao.findAll().switchMap { savedLocationEntities ->
            val backingLiveData = MutableLiveData<List<SavedLocation>>()
            GlobalScope.launch(Dispatchers.IO) {
                backingLiveData.postValue(savedLocationEntities.map { it.toUiModel() })
            }
            backingLiveData
        }
    }
}