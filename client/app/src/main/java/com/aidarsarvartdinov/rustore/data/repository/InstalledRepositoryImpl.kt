package com.aidarsarvartdinov.rustore.data.repository

import com.aidarsarvartdinov.rustore.data.datastore.DataStoreManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InstalledAppsRepositoryImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : InstalledAppsRepository {

    override fun isAppInstalled(appId: String): Flow<Boolean> =
        dataStoreManager.installedAppsFlow.map { it.contains(appId) }

    override suspend fun addInstalledApp(appId: String) =
        dataStoreManager.addInstalledApp(appId)

    override suspend fun removeInstalledApp(appId: String) =
        dataStoreManager.removeInstalledApp(appId)
}