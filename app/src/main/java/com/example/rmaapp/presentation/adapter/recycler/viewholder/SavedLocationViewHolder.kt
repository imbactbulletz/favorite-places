package com.example.rmaapp.presentation.adapter.recycler.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.rmaapp.presentation.model.SavedLocation
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item_location.*
import java.text.SimpleDateFormat

class SavedLocationViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    private lateinit var savedLocation: SavedLocation
    var onClickListener: ((SavedLocation) -> (Unit))? = null

    init {
        containerView.setOnClickListener { onClickListener?.invoke(savedLocation) }
    }

    fun onBind(savedLocation: SavedLocation) {
        this.savedLocation = savedLocation

        locationTitleTextView.text = savedLocation.title
        locationNoteTextView.text = savedLocation.note
        locationDateTakenTextView.text = SimpleDateFormat.getDateTimeInstance().format(savedLocation.dateCreated.time)
    }

}