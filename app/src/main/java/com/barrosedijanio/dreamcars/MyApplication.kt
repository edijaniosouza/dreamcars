package com.barrosedijanio.dreamcars

import android.app.Application
import com.barrosedijanio.dreamcars.di.cacheModule
import com.barrosedijanio.dreamcars.di.databaseModule
import com.barrosedijanio.dreamcars.di.preferencesModule
import com.barrosedijanio.dreamcars.di.serviceModule
import com.barrosedijanio.dreamcars.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class MyApplication : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            workManagerFactory()
            modules(databaseModule, serviceModule, viewModelModules, cacheModule, preferencesModule)
        }
    }
}