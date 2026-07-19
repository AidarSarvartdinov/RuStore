package com.aidarsarvartdinov.rustore.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class DataStoreManager(private val context: Context) {

    companion object {
        private val ONBOARDING_SHOWN_KEY = booleanPreferencesKey("onboarding_shown")
    }

    val isOnboardingShown: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            val value = preferences[ONBOARDING_SHOWN_KEY] ?: false
            value
        }

    suspend fun setOnboardingShown() {
        context.dataStore.edit { preferences ->
            preferences[ONBOARDING_SHOWN_KEY] = true
        }
    }
}