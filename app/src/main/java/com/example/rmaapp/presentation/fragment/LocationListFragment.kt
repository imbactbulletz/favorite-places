package com.example.rmaapp.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rmaapp.R
import com.example.rmaapp.presentation.adapter.recycler.SavedLocationListAdapter
import kotlinx.android.synthetic.main.activity_add_location.view.*
import kotlinx.android.synthetic.main.fragment_location_list.*

class LocationListFragment: Fragment(R.layout.fragment_location_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        locationRecyclerView.adapter = SavedLocationListAdapter()
        locationRecyclerView.layoutManager = LinearLayoutManager(activity)
    }

}