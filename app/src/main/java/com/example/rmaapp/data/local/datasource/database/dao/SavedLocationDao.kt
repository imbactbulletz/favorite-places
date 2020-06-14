package com.example.rmaapp.data.local.datasource.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.rmaapp.data.local.model.SavedLocationEntity

@Dao
abstract class SavedLocationDao {

    @Insert
    abstract fun save(savedLocationEntity: SavedLocationEntity)

    @Update
    abstract fun edit(savedLocationEntity: SavedLocationEntity)

    @Query("SELECT * FROM saved_location")
    abstract fun findAll(): LiveData<List<SavedLocationEntity>>
}