package com.example.rmaapp.presentation.model

import com.example.rmaapp.data.local.model.SavedLocationEntity
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import java.util.*

data class SavedLocation(
    private val title: String,
    val note: String,
    private val position: LatLng,
    val dateCreated: Date = Date(),
    val id: Int = 0
): ClusterItem {
    fun toDomainModel(): SavedLocationEntity {
        return SavedLocationEntity(title, note, position.latitude, position.longitude, dateCreated, id)
    }

    override fun getTitle(): String {
        return title
    }

    override fun getSnippet(): String {
        return note
    }

    override fun getPosition(): LatLng {
        return position
    }
}