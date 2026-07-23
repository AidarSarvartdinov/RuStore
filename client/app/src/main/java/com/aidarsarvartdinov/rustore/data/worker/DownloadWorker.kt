package com.aidarsarvartdinov.rustore.data.worker

import android.content.Context
import android.content.pm.ServiceInfo
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.aidarsarvartdinov.rustore.data.network.models.TaskStatus
import com.aidarsarvartdinov.rustore.utils.WorkerDependencies
import kotlinx.coroutines.delay
import java.io.File
import java.io.FileOutputStream
import java.util.UUID
import kotlin.time.Duration.Companion.milliseconds

class DownloadWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    companion object {
        const val KEY_APP_ID = "app_id"
        const val KEY_PROGRESS = "progress"
    }

    private val downloadApi = WorkerDependencies.downloadApi

    override suspend fun doWork(): Result {
        val appId = inputData.getString(KEY_APP_ID) ?: return Result.failure()

        setForeground(createForegroundInfo(0))

        return try {
            val taskResponse = downloadApi.startDownload(appId)
            val taskId = taskResponse.taskId

            var status = TaskStatus.PENDING
            var progress = 0
            while (status != TaskStatus.COMPLETED && status != TaskStatus.FAILED) {
                delay(1000.milliseconds)
                val statusResponse = downloadApi.getTaskStatus(taskId)
                status = statusResponse.status
                progress = statusResponse.progress ?: 0
                setForeground(createForegroundInfo(progress))
                setProgress(workDataOf(KEY_PROGRESS to progress))
            }

            if (status == TaskStatus.FAILED) {
                return Result.failure()
            }

            val response = downloadApi.downloadFile(taskId)
            if (!response.isSuccessful || response.body() == null) {
                return Result.failure()
            }

            val body = response.body()!!
            val apkFile = File(applicationContext.cacheDir, "app_${UUID.randomUUID()}.apk")
            body.byteStream().use { input ->
                FileOutputStream(apkFile).use { output ->
                    input.copyTo(output)
                }
            }

            Result.success(workDataOf("apk_path" to apkFile.absolutePath))

        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure()
        }
    }

    private fun createForegroundInfo(progress: Int): ForegroundInfo {
        val notification = NotificationCompat.Builder(applicationContext, "download_channel")
            .setContentTitle("Скачивание приложения")
            .setContentText("Прогресс: $progress%")
            .setSmallIcon(android.R.drawable.stat_sys_download)
            .setProgress(100, progress, false)
            .build()

        val serviceType = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC
        } else {
            0
        }

        return ForegroundInfo(1, notification, serviceType)
    }
}