package com.example.rmaapp.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rmaapp.R
import com.example.rmaapp.presentation.model.SavedLocation
import kotlinx.android.synthetic.main.activity_saved_location_info.*
import java.lang.NullPointerException
import java.text.SimpleDateFormat
import java.util.*

class LocationInfoActivity: AppCompatActivity(R.layout.activity_saved_location_info) {

    lateinit var savedLocation: SavedLocation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getSavedLocation()
        initViews()
    }

    private fun getSavedLocation() {
        val passedSavedLocation: SavedLocation?  = intent.getParcelableExtra(SAVED_LOCATION_KEY)

        if(passedSavedLocation != null) savedLocation = passedSavedLocation
        else throw NullPointerException("An instance of SavedLocation must be passed as a parameter.")
    }

    private fun initViews() {
        titleTextView.text = savedLocation.title
        noteTextView.text = savedLocation.note
        dateCreatedTextView.text = SimpleDateFormat("dd.MM.yyyy. HH:mm", Locale.getDefault()).format(savedLocation.dateCreated)
    }

    companion object {
        const val SAVED_LOCATION_KEY = "SAVED_LOCATION_KEY"
    }
}