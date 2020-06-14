package com.example.rmaapp.presentation.adapter.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.rmaapp.R
import com.example.rmaapp.presentation.adapter.recycler.viewholder.SavedLocationViewHolder
import com.example.rmaapp.presentation.model.SavedLocation

class SavedLocationListAdapter: ListAdapter<SavedLocation, SavedLocationViewHolder>(
    SAVED_LOCATION_DIFF_UTIL_ITEM_CALLBACK) {

    companion object {
        private val SAVED_LOCATION_DIFF_UTIL_ITEM_CALLBACK = object: DiffUtil.ItemCallback<SavedLocation>() {
            override fun areItemsTheSame(oldItem: SavedLocation, newItem: SavedLocation): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: SavedLocation,
                newItem: SavedLocation
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedLocationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_location, parent, false)
        return SavedLocationViewHolder(view)
    }

    override fun onBindViewHolder(holder: SavedLocationViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}