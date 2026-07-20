package com.aidarsarvartdinov.server.rustore.dto

data class TaskStatusResponse(
    val taskId: String,
    val status: TaskStatus,
    val progress: Int? = null,
    val errorMessage: String? = null
)
