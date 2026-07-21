package com.aidarsarvartdinov.rustore.data.repository

import android.content.Context
import com.aidarsarvartdinov.rustore.data.network.DownloadApi
import com.aidarsarvartdinov.rustore.data.network.models.TaskStatus
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.Duration.Companion.milliseconds

@Singleton
class DownloadRepositoryImpl @Inject constructor(
    private val api: DownloadApi,
    @ApplicationContext private val context: Context
) : DownloadRepository {

    override fun downloadApp(appId: String): Flow<DownloadProgress> = flow {
        val taskResponse = api.startDownload()
        val taskId = taskResponse.taskId

        var isFinished = false

        while (!isFinished) {
            val status = api.getTaskStatus(taskId)
            emit(
                DownloadProgress(
                    taskId = taskId,
                    status = status.status,
                    progress = status.progress ?: 0,
                    errorMessage = status.errorMessage
                )
            )

            when (status.status) {
                TaskStatus.COMPLETED -> {
                    val file = downloadFile(taskId)

                    emit(
                        DownloadProgress(
                            taskId = taskId,
                            status = TaskStatus.COMPLETED,
                            progress = 100,
                            downloadedFile = file
                        )
                    )
                    isFinished = true
                }
                TaskStatus.FAILED -> {
                    isFinished = true
                }
                else -> {
                    delay(1000.milliseconds)
                }
            }
        }
    }.catch { e ->
        emit(
            DownloadProgress(
                taskId = "",
                status = TaskStatus.FAILED,
                errorMessage = e.message ?: "Unknown error"
            )
        )
    }.flowOn(Dispatchers.IO)

    private suspend fun downloadFile(taskId: String): File {
        val response = api.downloadFile(taskId)
        if (!response.isSuccessful) throw Exception("Download error: ${response.code()}")

        val body = response.body() ?: throw Exception("Empty response")
        val fileName = "app_$taskId.apk"
        val file = File(context.cacheDir, fileName)

        body.byteStream().use { inputStream ->
            file.outputStream().use { outputStream ->
                inputStream.copyTo(outputStream)
            }
        }
        return file
    }

    override suspend fun cancelDownload(taskId: String) {
        try {
            api.cancelTask(taskId)
        } catch (e: Exception) {

        }
    }
}