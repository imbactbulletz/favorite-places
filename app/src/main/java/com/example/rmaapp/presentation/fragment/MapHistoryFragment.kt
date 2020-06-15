package com.example.rmaapp.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.rmaapp.presentation.contract.MapHistoryContract
import com.example.rmaapp.presentation.viewmodel.MapHistoryFragmentViewModel
import com.google.android.gms.maps.SupportMapFragment
import org.koin.android.viewmodel.ext.android.viewModel
import androidx.lifecycle.observe

class MapHistoryFragment: SupportMapFragment() {

    private val viewModel: MapHistoryContract.ViewModel by viewModel<MapHistoryFragmentViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.findAll().observe(viewLifecycleOwner) {
            Log.d("Main","${it.size}")
        }
    }
}