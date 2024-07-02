package com.barrosedijanio.dreamcars.navigation

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map


class Session(private val datastore: DataStore<Preferences>){

    companion object {
        const val USER_PREFERENCES = "user_preferences"
        private const val USER_ID = "user_id"
        private const val ISLOGGED = "isLogged"
        val userId = intPreferencesKey(USER_ID)
        val isLogged = booleanPreferencesKey(ISLOGGED)
    }


    fun isUserLoggedIn(): Flow<Boolean> {
        return datastore.data.catch {
            emit(emptyPreferences())
        }.map { preference ->
            Log.i("testLogin", "session: ${preference[isLogged]} ")
            preference[isLogged] ?: false
        }
    }

    suspend fun setUserLogin(isUserLoggedIn: Boolean){
        datastore.edit { preference ->
            preference[isLogged] = isUserLoggedIn
        }
    }

    fun getUserId(): Flow<Int> {
        return datastore.data.catch {
            emit(emptyPreferences())
        }.map { preferences ->
            preferences[userId] ?: 0
        }
    }

    suspend fun setUserId(id: Int) {
        setUserLogin(true)
        datastore.edit { preference ->
            preference[userId] = id
        }
    }
}