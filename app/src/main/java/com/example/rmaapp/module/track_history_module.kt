package com.example.rmaapp.module

import com.example.rmaapp.presentation.viewmodel.LocationListFragmentViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val trackHistoryModule = module {
    viewModel { LocationListFragmentViewModel(get()) }
}