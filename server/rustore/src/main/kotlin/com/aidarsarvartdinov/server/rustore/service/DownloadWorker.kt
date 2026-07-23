package com.aidarsarvartdinov.server.rustore.service

import com.aidarsarvartdinov.server.rustore.dto.TaskStatus
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.io.File
import java.io.FileOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

@Component
class DownloadWorker(
    private val taskService: TaskService,
    private val appService: AppService
) {

    @Async
    fun processDownload(taskId: String, appId: String) {
        try {
            taskService.updateTaskStatus(taskId, TaskStatus.IN_PROGRESS)

            // loading simulation
            for (i in 0..100 step 10) {
                Thread.sleep(500)
                taskService.updateTaskProgress(taskId, i)
            }

            val appDetails = appService.getAppDetails(appId)
            val appName = appDetails?.name ?: "Unknown"

            val tempFile = File.createTempFile("app_", ".apk")

            ZipOutputStream(FileOutputStream(tempFile)).use { zos ->
                val entry = ZipEntry("app_name.txt")
                zos.putNextEntry(entry)
                zos.write(appName.toByteArray())
                zos.closeEntry()
            }

            taskService.completeTask(taskId, tempFile.absolutePath)

        } catch (e: Exception) {
            taskService.failTask(taskId, "Download error: ${e.message}")
        }
    }
}