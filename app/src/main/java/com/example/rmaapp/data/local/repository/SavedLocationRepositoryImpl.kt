package com.example.rmaapp.data.local.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.example.rmaapp.data.local.datasource.database.dao.SavedLocationDao
import com.example.rmaapp.data.local.model.SavedLocationEntity
import com.example.rmaapp.presentation.model.SavedLocation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SavedLocationRepositoryImpl(private val savedLocationDao: SavedLocationDao) :
    SavedLocationRepository {

    override fun save(savedLocation: SavedLocation) {
        savedLocationDao.save(
            SavedLocationEntity(
                savedLocation.title,
                savedLocation.note,
                savedLocation.dateCreated
            )
        )
    }

    override fun edit(savedLocation: SavedLocation) {
        savedLocationDao.edit(
            SavedLocationEntity(
                savedLocation.title,
                savedLocation.note,
                savedLocation.dateCreated,
                savedLocation.id
            )
        )
    }

    override fun findAll(): LiveData<List<SavedLocation>> {
        return savedLocationDao.findAll().switchMap { savedLocationEntities ->
            val backingLiveData = MutableLiveData<List<SavedLocation>>()
            GlobalScope.launch(Dispatchers.IO) {
                backingLiveData.postValue(savedLocationEntities.map {
                    SavedLocation(
                        it.title,
                        it.note,
                        it.dateCreated,
                        it.id
                    )
                })
            }
            backingLiveData
        }
    }
}