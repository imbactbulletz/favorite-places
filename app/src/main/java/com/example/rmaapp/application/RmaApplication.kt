package com.example.rmaapp.application

import android.app.Application
import com.example.rmaapp.module.addLocationModule
import com.example.rmaapp.module.coreModule
import com.example.rmaapp.module.trackHistoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class RmaApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        initKoin()
    }

    private fun initKoin() {
        val modules = listOf(coreModule, addLocationModule, trackHistoryModule)

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@RmaApplication)
            androidFileProperties()
            fragmentFactory()
            modules(modules)
        }
    }
}