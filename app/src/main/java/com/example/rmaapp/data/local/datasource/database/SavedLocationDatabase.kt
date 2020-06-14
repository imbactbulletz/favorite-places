package com.example.rmaapp.data.local.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rmaapp.data.local.datasource.database.dao.SavedLocationDao
import com.example.rmaapp.data.local.model.SavedLocationEntity

@Database(
    entities = [SavedLocationEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SavedLocationDatabase: RoomDatabase() {

    abstract fun getSavedLocationDao(): SavedLocationDao
}