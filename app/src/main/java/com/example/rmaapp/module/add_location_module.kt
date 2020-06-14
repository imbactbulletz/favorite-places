package com.example.rmaapp.module

import com.example.rmaapp.data.local.datasource.database.SavedLocationDatabase
import com.example.rmaapp.data.local.datasource.database.dao.SavedLocationDao
import com.example.rmaapp.data.local.repository.SavedLocationRepository
import com.example.rmaapp.data.local.repository.SavedLocationRepositoryImpl
import com.example.rmaapp.presentation.viewmodel.AddLocationViewModel
import org.koin.android.experimental.dsl.viewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val addLocationModule = module {
    viewModel { AddLocationViewModel(get())}

    single<SavedLocationRepository> { SavedLocationRepositoryImpl(get())}

    single { get<SavedLocationDatabase>().getSavedLocationDao() }
}