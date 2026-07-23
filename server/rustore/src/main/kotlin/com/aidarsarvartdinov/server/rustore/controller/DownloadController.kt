package com.aidarsarvartdinov.server.rustore.controller

import com.aidarsarvartdinov.server.rustore.dto.TaskResponse
import com.aidarsarvartdinov.server.rustore.dto.TaskStatus
import com.aidarsarvartdinov.server.rustore.dto.TaskStatusResponse
import com.aidarsarvartdinov.server.rustore.service.DownloadWorker
import com.aidarsarvartdinov.server.rustore.service.TaskService
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.io.File

@RestController
@RequestMapping("/api/download")
class DownloadController(
    private val taskService: TaskService,
    private val downloadWorker: DownloadWorker
) {
    @PostMapping("/start")
    fun startDownload(@RequestParam appId: String): TaskResponse {
        val taskId = taskService.createTask(appId)
        downloadWorker.processDownload(taskId, appId)
        return TaskResponse(taskId)
    }

    @GetMapping("/status/{taskId}")
    fun getTaskStatus(@PathVariable taskId: String): ResponseEntity<TaskStatusResponse> {
        val task = taskService.getTask(taskId) ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(
            TaskStatusResponse(
                taskId = task.id,
                status = task.status,
                progress = task.progress,
                errorMessage = task.errorMessage
            )
        )
    }

    @DeleteMapping("/cancel/{taskId}")
    fun cancelTask(@PathVariable taskId: String): ResponseEntity<Unit> {
        val task = taskService.getTask(taskId) ?: return ResponseEntity.notFound().build()

        taskService.failTask(taskId, "Задача отменена пользователем")
        return ResponseEntity.ok().build()
    }

    @GetMapping("/file/{taskId}")
    fun downloadFile(@PathVariable taskId: String): ResponseEntity<Resource> {
        val task = taskService.getTask(taskId)

        if (task == null || task.status != TaskStatus.COMPLETED) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build()
        }

        val file = File(task.result ?: return ResponseEntity.notFound().build())
        if (!file.exists()) {
            return ResponseEntity.notFound().build()
        }

        val resource = FileSystemResource(file)
        val headers = HttpHeaders().apply {
            add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"${file.name}\"")
            contentType = MediaType.APPLICATION_OCTET_STREAM
        }

        return ResponseEntity.ok()
            .headers(headers)
            .contentLength(file.length())
            .body(resource)
    }
}