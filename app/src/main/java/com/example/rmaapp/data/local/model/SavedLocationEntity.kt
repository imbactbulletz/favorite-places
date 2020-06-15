package com.example.rmaapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rmaapp.presentation.model.SavedLocation
import com.google.android.gms.maps.model.LatLng
import java.util.*

@Entity(tableName = "saved_location")
data class SavedLocationEntity(
    val title: String,
    val note: String,
    val latitude: Double,
    val longitude: Double,
    val dateCreated: Date,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) {
    fun toUiModel(): SavedLocation {
        return SavedLocation(title, note, LatLng(latitude, longitude), dateCreated, id)
    }

}