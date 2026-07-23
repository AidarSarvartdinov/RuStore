package com.aidarsarvartdinov.rustore.data.repository

import kotlinx.coroutines.flow.Flow

interface InstalledAppsRepository {
    fun isAppInstalled(appId: String): Flow<Boolean>
    suspend fun addInstalledApp(appId: String)
    suspend fun removeInstalledApp(appId: String)
}