package com.example.rmaapp.module

import com.example.rmaapp.presentation.viewmodel.MapHistoryFragmentViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mapHistoryModule = module {

    viewModel { MapHistoryFragmentViewModel(get()) }
}