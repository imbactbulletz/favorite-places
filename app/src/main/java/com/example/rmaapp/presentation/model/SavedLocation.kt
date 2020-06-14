package com.example.rmaapp.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class SavedLocation(val title: String, val note: String, val dateCreated: Date = Date(), val id: Int = 0) : Parcelable