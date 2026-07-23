package com.aidarsarvartdinov.server.rustore.service

import com.aidarsarvartdinov.server.rustore.dto.TaskStatus
import org.springframework.stereotype.Service
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

@Service
class TaskService {
    data class Task(
        val id: String,
        val appId: String,
        var status: TaskStatus = TaskStatus.PENDING,
        var progress: Int = 0,
        var errorMessage: String? = null,
        var result: String? = null
    )

    private val tasks = ConcurrentHashMap<String, Task>()

    fun createTask(appId: String): String {
        val taskId = UUID.randomUUID().toString()
        tasks[taskId] = Task(taskId, appId)
        return taskId
    }

    fun getTask(taskId: String): Task? = tasks[taskId]

    fun updateTaskStatus(taskId: String, status: TaskStatus) {
        tasks[taskId]?.status = status
    }

    fun updateTaskProgress(taskId: String, progress: Int) {
        tasks[taskId]?.progress = progress
    }

    fun completeTask(taskId: String, resultPath: String) {
        val task = tasks[taskId]
        task?.status = TaskStatus.COMPLETED
        task?.progress = 100
        task?.result = resultPath
    }

    fun failTask(taskId: String, errorMessage: String) {
        val task = tasks[taskId]
        task?.status = TaskStatus.FAILED
        task?.errorMessage = errorMessage
    }
}
