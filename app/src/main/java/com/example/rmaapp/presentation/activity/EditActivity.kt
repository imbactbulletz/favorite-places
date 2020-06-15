package com.example.rmaapp.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rmaapp.R
import com.example.rmaapp.extension.toTimeStamp
import com.example.rmaapp.presentation.contract.EditContract
import com.example.rmaapp.presentation.model.SavedLocation
import com.example.rmaapp.presentation.viewmodel.EditActivityViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_edit.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.lang.NullPointerException

class EditActivity: AppCompatActivity(R.layout.activity_edit) {

    companion object {
        const val SAVED_LOCATION_KEY = "SAVED_LOCATION_KEY"
    }

    private lateinit var savedLocation: SavedLocation

    private val viewModel: EditContract.ViewModel by viewModel<EditActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getSavedLocation()
        initViews()
    }

    private fun initViews() {
        initButtonListeners()
        displaySavedLocationInformation()
    }

    private fun initButtonListeners() {
        discardButton.setOnClickListener {
            showConfirmationDialog()
        }

        saveButton.setOnClickListener {
            val titleText = locationTitleEditText.editableText.toString()
            val noteText = locationNoteEditText.editableText.toString()

            if(titleText.isNotEmpty() || noteText.isNotEmpty()) {
                viewModel.edit(savedLocation.copy(title = titleText, note = noteText))
            } else {
                if (titleText.isEmpty()) locationTitleEditText.error = "Title must not be empty"
                if (noteText.isEmpty()) locationNoteEditText.error = "Note must not be empty"
            }
        }
    }

    private fun showConfirmationDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Discard edit action")
            .setMessage("Are you sure you want to discard editing of this entry?")
            .setPositiveButton("Yes") { _,_ -> finish() }
            .setNegativeButton("No", null)
            .show()
    }

    private fun displaySavedLocationInformation() {
        locationTitleEditText.setText(savedLocation.title)
        locationNoteEditText.setText(savedLocation.note)
        dateTextView.text = savedLocation.dateCreated.toTimeStamp()
    }

    private fun getSavedLocation() {
        val passedSavedLocation: SavedLocation? = intent.getParcelableExtra(SAVED_LOCATION_KEY)
        if(passedSavedLocation != null) savedLocation = passedSavedLocation
        else throw NullPointerException("Saved location must be passed as a parameter.")
    }
}