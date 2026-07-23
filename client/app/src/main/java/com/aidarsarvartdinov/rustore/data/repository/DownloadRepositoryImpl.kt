package com.aidarsarvartdinov.rustore.data.repository

import android.content.Context
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.aidarsarvartdinov.rustore.data.network.models.TaskStatus
import com.aidarsarvartdinov.rustore.data.worker.DownloadWorker
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.File
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DownloadRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : DownloadRepository {

    private val workManager: WorkManager by lazy { WorkManager.getInstance(context) }

    override fun downloadApp(appId: String): Flow<DownloadProgress> = flow {
        val inputData = Data.Builder()
            .putString(DownloadWorker.KEY_APP_ID, appId)
            .build()

        val workRequest = OneTimeWorkRequestBuilder<DownloadWorker>()
            .setInputData(inputData)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .build()

        workManager.enqueue(workRequest)

        workManager.getWorkInfoByIdFlow(workRequest.id).collect { info ->
            when (info?.state) {
                WorkInfo.State.RUNNING -> {
                    val progress = info.progress.getInt(DownloadWorker.KEY_PROGRESS, 0)
                    emit(
                        DownloadProgress(
                            taskId = workRequest.id.toString(),
                            status = TaskStatus.IN_PROGRESS,
                            progress = progress
                        )
                    )
                }
                WorkInfo.State.SUCCEEDED -> {
                    val apkPath = info.outputData.getString("apk_path") ?: ""
                    val apkFile = if (apkPath.isNotEmpty()) File(apkPath) else null
                    emit(
                        DownloadProgress(
                            taskId = workRequest.id.toString(),
                            status = TaskStatus.COMPLETED,
                            apkFile = apkFile
                        )
                    )
                }
                WorkInfo.State.CANCELLED -> {
                    emit(DownloadProgress(
                        taskId = workRequest.id.toString(),
                        status = TaskStatus.CANCELLED
                    ))
                }
                WorkInfo.State.FAILED -> {
                    emit(
                        DownloadProgress(
                            taskId = workRequest.id.toString(),
                            status = TaskStatus.FAILED,
                            errorMessage = "Ошибка загрузки"
                        )
                    )
                }
                else -> { /* ignore */ }
            }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun cancelDownload(taskId: String) {
        val workId = UUID.fromString(taskId)
        workManager.cancelWorkById(workId)
    }

    override fun getProgress(taskId: String): Flow<DownloadProgress> = flow {
        val workId = UUID.fromString(taskId)
        workManager.getWorkInfoByIdFlow(workId).collect { info ->
            when (info?.state) {
                WorkInfo.State.RUNNING -> {
                    val progress = info.progress.getInt(DownloadWorker.KEY_PROGRESS, 0)
                    emit(DownloadProgress(
                        taskId = taskId,
                        status = TaskStatus.IN_PROGRESS,
                        progress = progress
                    ))
                }
                WorkInfo.State.SUCCEEDED -> {
                    val apkPath = info.outputData.getString("apk_path") ?: ""
                    val apkFile = if (apkPath.isNotEmpty()) File(apkPath) else null
                    emit(DownloadProgress(
                        taskId = taskId,
                        status = TaskStatus.COMPLETED,
                        apkFile = apkFile
                    ))
                }
                WorkInfo.State.CANCELLED -> {
                    emit(DownloadProgress(
                        taskId = taskId,
                        status = TaskStatus.CANCELLED
                    ))
                }
                WorkInfo.State.FAILED -> {
                    emit(DownloadProgress(
                        taskId = taskId,
                        status = TaskStatus.FAILED,
                        errorMessage = "Ошибка загрузки"
                    ))
                }
                else -> { /* ignore */ }
            }
        }
    }.flowOn(Dispatchers.IO)
}