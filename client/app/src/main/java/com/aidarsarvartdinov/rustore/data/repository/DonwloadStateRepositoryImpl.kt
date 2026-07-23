package com.aidarsarvartdinov.rustore.data.repository

import com.aidarsarvartdinov.rustore.data.datastore.DataStoreManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DownloadStateRepositoryImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : DownloadStateRepository {

    override fun getTaskId(appId: String): Flow<String?> =
        dataStoreManager.getTaskId(appId)

    override suspend fun saveTaskId(appId: String, taskId: String) =
        dataStoreManager.saveTaskId(appId, taskId)

    override suspend fun removeTaskId(appId: String) =
        dataStoreManager.removeTaskId(appId)

    override fun getApkPath(appId: String): Flow<String?> =
        dataStoreManager.getApkPath(appId)

    override suspend fun saveApkPath(appId: String, path: String) =
        dataStoreManager.saveApkPath(appId, path)

    override suspend fun removeApkPath(appId: String) =
        dataStoreManager.removeApkPath(appId)
}