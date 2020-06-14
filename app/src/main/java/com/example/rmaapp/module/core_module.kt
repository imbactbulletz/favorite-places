package com.example.rmaapp.module

import androidx.room.Room
import com.example.rmaapp.data.local.datasource.database.SavedLocationDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            SavedLocationDatabase::class.java,
            "SavedLocationsDB"
        ).fallbackToDestructiveMigration()
         .build()
    }
}