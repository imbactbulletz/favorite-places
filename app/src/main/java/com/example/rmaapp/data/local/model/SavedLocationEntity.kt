package com.example.rmaapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_location")
data class SavedLocationEntity (
    val title: String,
    val note: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)