package com.vs.february2026_challenge.last_active

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_settings")

class UserPreferencesRepository(private val context: Context) {
    private val LAST_ACTIVE_KEY = longPreferencesKey("last_active_timestamp")

    // Flow to retrieve the timestamp
    val lastActiveTimestamp: Flow<Long?> = context.dataStore.data
        .map { preferences ->
            preferences[LAST_ACTIVE_KEY]
        }

    // Function to save the timestamp (called on app background)
    suspend fun saveLastActive(timestamp: Long) {
        context.dataStore.edit { preferences ->
            preferences[LAST_ACTIVE_KEY] = timestamp
        }
    }
}