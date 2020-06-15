package com.example.rmaapp.presentation.fragment

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rmaapp.R
import com.example.rmaapp.presentation.activity.LocationInfoActivity
import com.example.rmaapp.presentation.adapter.recycler.SavedLocationListAdapter
import com.example.rmaapp.presentation.contract.LocationListContract
import com.example.rmaapp.presentation.model.SavedLocation
import com.example.rmaapp.presentation.viewmodel.LocationListFragmentViewModel
import kotlinx.android.synthetic.main.fragment_location_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class LocationListFragment : Fragment(R.layout.fragment_location_list) {

    companion object {
        private const val INITIAL_POSITION = 0F
    }

    private val viewModel: LocationListContract.ViewModel by viewModel<LocationListFragmentViewModel>()

    private lateinit var recyclerAdapter: SavedLocationListAdapter

    private var sortButtonRotationAngle = INITIAL_POSITION

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtonListeners()
        initRecyclerView()
        initObservers()
    }

    private fun initButtonListeners() {
        sortButton.setOnClickListener {
            sortAdapterDataSetByDate(sortButtonRotationAngle == INITIAL_POSITION)
            rotateSortButton()
        }
    }

    private fun sortAdapterDataSetByDate(ascending: Boolean) {
        val adapterList = recyclerAdapter.currentList.toMutableList()
        if(recyclerAdapter.currentList.isNotEmpty()) {
            when(ascending) {
                true -> adapterList.sortBy { it.dateCreated }
                false -> adapterList.sortByDescending { it.dateCreated }
            }

            recyclerAdapter.submitList(adapterList) { locationRecyclerView.scrollToPosition(0) }
        }
    }


    private fun rotateSortButton() {

        sortButtonRotationAngle += 180F
        val sortButtonAnimator = ObjectAnimator.ofFloat(sortButton, "rotation", sortButtonRotationAngle)
        sortButtonAnimator.duration = 500
        sortButtonAnimator.interpolator = AccelerateDecelerateInterpolator()
        sortButtonAnimator.start()
        sortButtonAnimator.doOnEnd {
            if(sortButtonRotationAngle == 360F) {
                sortButton.rotation = 0F
                sortButtonRotationAngle = INITIAL_POSITION
            }
        }
    }

    private fun initRecyclerView() {
        recyclerAdapter = SavedLocationListAdapter { startLocationInfoActivity(it) }
        locationRecyclerView.adapter = recyclerAdapter
        locationRecyclerView.layoutManager = LinearLayoutManager(activity)
    }

    private fun initObservers() {
        viewModel.getAllSavedLocations().observe(viewLifecycleOwner) { locations ->
            recyclerAdapter.submitList(locations.sortedByDescending { it.dateCreated }) {
                locationRecyclerView.scrollToPosition(0)
            }
        }
    }

    private fun startLocationInfoActivity(savedLocation: SavedLocation) {
        val intent = Intent(activity, LocationInfoActivity::class.java)
        intent.putExtra(LocationInfoActivity.SAVED_LOCATION_ID_KEY, savedLocation.id)
        startActivity(intent)
    }
}