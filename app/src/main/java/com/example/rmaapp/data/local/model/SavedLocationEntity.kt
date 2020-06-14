package com.example.rmaapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "saved_location")
data class SavedLocationEntity (
    val title: String,
    val note: String,
    val dateCreated: Date,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)