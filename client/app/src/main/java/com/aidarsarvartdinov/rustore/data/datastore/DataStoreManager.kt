package com.aidarsarvartdinov.rustore.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class DataStoreManager(private val context: Context) {

    companion object {
        private val ONBOARDING_SHOWN_KEY = booleanPreferencesKey("onboarding_shown")
        private val INSTALLED_APPS_KEY = stringSetPreferencesKey("installed_apps")
        private val APK_PATH_KEY_PREFIX = "apk_path_"
        private val TASK_ID_KEY_PREFIX = "task_id_"
    }

    val isOnboardingShown: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[ONBOARDING_SHOWN_KEY] ?: false
        }

    suspend fun setOnboardingShown() {
        context.dataStore.edit { preferences ->
            preferences[ONBOARDING_SHOWN_KEY] = true
        }
    }

    val installedAppsFlow: Flow<Set<String>> = context.dataStore.data
        .map { preferences ->
            preferences[INSTALLED_APPS_KEY] ?: emptySet()
        }

    suspend fun addInstalledApp(appId: String) {
        context.dataStore.edit { preferences ->
            val current = preferences[INSTALLED_APPS_KEY] ?: emptySet()
            preferences[INSTALLED_APPS_KEY] = current + appId
        }
    }

    suspend fun removeInstalledApp(appId: String) {
        context.dataStore.edit { preferences ->
            val current = preferences[INSTALLED_APPS_KEY] ?: emptySet()
            preferences[INSTALLED_APPS_KEY] = current - appId
        }
    }

    suspend fun saveApkPath(appId: String, path: String) {
        context.dataStore.edit { preferences ->
            preferences[stringPreferencesKey(APK_PATH_KEY_PREFIX + appId)] = path
        }
    }

    fun getApkPath(appId: String): Flow<String?> =
        context.dataStore.data.map { preferences ->
            preferences[stringPreferencesKey(APK_PATH_KEY_PREFIX + appId)]
        }

    suspend fun removeApkPath(appId: String) {
        context.dataStore.edit { preferences ->
            preferences.remove(stringPreferencesKey(APK_PATH_KEY_PREFIX + appId))
        }
    }

    suspend fun saveTaskId(appId: String, taskId: String) {
        context.dataStore.edit { preferences ->
            preferences[stringPreferencesKey(TASK_ID_KEY_PREFIX + appId)] = taskId
        }
    }

    fun getTaskId(appId: String): Flow<String?> =
        context.dataStore.data.map { preferences ->
            preferences[stringPreferencesKey(TASK_ID_KEY_PREFIX + appId)]
        }

    suspend fun removeTaskId(appId: String) {
        context.dataStore.edit { preferences ->
            preferences.remove(stringPreferencesKey(TASK_ID_KEY_PREFIX + appId))
        }
    }
}