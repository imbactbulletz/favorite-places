package com.example.rmaapp.module

import com.example.rmaapp.presentation.viewmodel.LocationInfoActivityViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val locationInfoModule = module {

    viewModel { LocationInfoActivityViewModel(get())}

}