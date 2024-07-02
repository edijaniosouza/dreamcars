package com.barrosedijanio.dreamcars.di

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

const val USER_PREFERENCES = "user_preferences"
val USER_ID = intPreferencesKey("user_id")

val preferencesModule = module {
    single {
        PreferenceDataStoreFactory.create {
            androidContext().preferencesDataStoreFile(USER_PREFERENCES)
        }
    }
}