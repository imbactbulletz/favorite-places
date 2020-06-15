package com.example.rmaapp.module

import com.example.rmaapp.presentation.viewmodel.EditActivityViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val editModule = module {

    viewModel { EditActivityViewModel(get()) }

}