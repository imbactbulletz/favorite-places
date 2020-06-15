package com.example.rmaapp.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rmaapp.R
import com.example.rmaapp.extension.toTimeStamp
import com.example.rmaapp.presentation.contract.LocationInfoContract
import com.example.rmaapp.presentation.model.SavedLocation
import com.example.rmaapp.presentation.viewmodel.LocationInfoActivityViewModel
import kotlinx.android.synthetic.main.activity_saved_location_info.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.lang.NullPointerException
import androidx.lifecycle.observe

class LocationInfoActivity: AppCompatActivity(R.layout.activity_saved_location_info) {

    companion object {
        const val SAVED_LOCATION_ID_KEY = "SAVED_LOCATION_KEY"
    }

    lateinit var savedLocation: SavedLocation

    private val viewModel: LocationInfoContract.ViewModel by viewModel<LocationInfoActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getSavedLocation()
        initViews()
    }

    private fun getSavedLocation() {
        val passedSavedLocationId: Int?  = intent.getIntExtra(SAVED_LOCATION_ID_KEY, -1)

        if(passedSavedLocationId != null && passedSavedLocationId != -1) {
            viewModel.findById(passedSavedLocationId).observe(this) {
                this.savedLocation = it
                displaySavedLocationInfo()
            }
        } else throw NullPointerException("An instance of SavedLocation must be passed as a parameter.")
    }

    private fun displaySavedLocationInfo() {
        titleTextView.text = savedLocation.title
        noteTextView.text = savedLocation.note
        dateCreatedTextView.text = savedLocation.dateCreated.toTimeStamp()
    }

    private fun initViews() {
        initButtonListeners()
    }

    private fun initButtonListeners() {
        editButton.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            intent.putExtra(EditActivity.SAVED_LOCATION_ID_KEY, savedLocation.id)
            startActivity(intent)
        }
    }
}