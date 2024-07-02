package com.barrosedijanio.dreamcars.di

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import com.barrosedijanio.dreamcars.navigation.Session
import com.barrosedijanio.dreamcars.navigation.Session.Companion.USER_PREFERENCES
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val sessionModule = module {
    single {
        PreferenceDataStoreFactory.create {
            androidContext().preferencesDataStoreFile(USER_PREFERENCES)
        }
    }

    single {
        Session(get())
    }
}