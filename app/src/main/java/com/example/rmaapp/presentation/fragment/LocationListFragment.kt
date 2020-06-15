package com.example.rmaapp.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
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

    private val viewModel: LocationListContract.ViewModel by viewModel<LocationListFragmentViewModel>()

    private lateinit var recyclerAdapter: SavedLocationListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initObservers()
    }

    private fun initRecyclerView() {
        recyclerAdapter = SavedLocationListAdapter { startLocationInfoActivity(it) }
        locationRecyclerView.adapter = recyclerAdapter
        locationRecyclerView.layoutManager = LinearLayoutManager(activity)
    }

    private fun initObservers() {
        viewModel.getAllSavedLocations().observe(viewLifecycleOwner) { locations ->
            recyclerAdapter.submitList(locations)
        }
    }

    private fun startLocationInfoActivity(savedLocation: SavedLocation) {
        val intent = Intent(activity, LocationInfoActivity::class.java)
        intent.putExtra(LocationInfoActivity.SAVED_LOCATION_ID_KEY, savedLocation.id)
        startActivity(intent)
    }
}