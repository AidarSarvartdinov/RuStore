package com.aidarsarvartdinov.rustore.data.repository

import com.aidarsarvartdinov.rustore.data.network.models.TaskStatus
import kotlinx.coroutines.flow.Flow
import java.io.File

interface DownloadRepository {
    fun downloadApp(appId: String): Flow<DownloadProgress>
    suspend fun cancelDownload(taskId: String)
}

data class DownloadProgress(
    val taskId: String,
    val status: TaskStatus,
    val progress: Int = 0,
    val errorMessage: String? = null,
    val downloadedFile: File? = null
)