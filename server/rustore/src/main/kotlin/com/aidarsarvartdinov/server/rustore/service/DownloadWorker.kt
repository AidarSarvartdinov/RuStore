package com.aidarsarvartdinov.server.rustore.service

import com.aidarsarvartdinov.server.rustore.dto.TaskStatus
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.io.File

@Component
class DownloadWorker(
    private val taskService: TaskService
) {

    @Async
    fun processDownload(taskId: String) {
        try {
            taskService.updateTaskStatus(taskId, TaskStatus.IN_PROGRESS)

            // loading simulation
            for (i in 0..100 step 10) {
                Thread.sleep(500)
                taskService.updateTaskProgress(taskId, i)
            }

            val tempFile = File.createTempFile("app_", ".apk")

            tempFile.writeBytes("Test APK file".toByteArray())

            taskService.completeTask(taskId, tempFile.absolutePath)

        } catch (e: Exception) {
            taskService.failTask(taskId, "Download error: ${e.message}")
        }
    }
}