package com.aidarsarvartdinov.rustore.data.repository

import kotlinx.coroutines.flow.Flow

interface DownloadStateRepository {
    fun getTaskId(appId: String): Flow<String?>
    suspend fun saveTaskId(appId: String, taskId: String)
    suspend fun removeTaskId(appId: String)

    fun getApkPath(appId: String): Flow<String?>
    suspend fun saveApkPath(appId: String, path: String)
    suspend fun removeApkPath(appId: String)
}