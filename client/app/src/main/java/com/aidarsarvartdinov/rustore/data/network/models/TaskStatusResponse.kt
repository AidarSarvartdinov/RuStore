package com.aidarsarvartdinov.rustore.data.network.models

data class TaskStatusResponse(
    val taskId: String,
    val status: TaskStatus,
    val progress: Int? = null,
    val errorMessage: String? = null
)
