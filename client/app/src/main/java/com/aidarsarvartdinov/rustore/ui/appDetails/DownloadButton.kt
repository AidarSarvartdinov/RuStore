package com.aidarsarvartdinov.rustore.ui.appDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.aidarsarvartdinov.rustore.data.network.models.TaskStatus
import com.aidarsarvartdinov.rustore.data.repository.DownloadProgress

@Composable
fun DownloadButton(
    downloadState: DownloadProgress?,
    onDownloadClick: () -> Unit,
    onCancelClick: () -> Unit,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = ButtonDefaults.shape
) {
    when (downloadState?.status) {
        null -> {
            Button(
                onClick = onDownloadClick,
                modifier = modifier,
                shape = shape
            ) {
                Text("Установить")
            }
        }

        TaskStatus.PENDING, TaskStatus.IN_PROGRESS -> {
            Column(
                modifier = modifier
            ) {
                Button(
                    onClick = { /* button is blocked */ },
                    modifier = Modifier.fillMaxWidth(),
                    shape = shape,
                    enabled = false
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.weight(1f),
                        color = Color.White,
                        strokeWidth = 2.dp
                    )
                    Text(
                        text = " ${downloadState.progress}%",
                        modifier = Modifier.weight(1f)
                    )
                }

                Button(
                    onClick = onCancelClick,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Отменить")
                }
            }
        }

        TaskStatus.COMPLETED -> {
            Button(
                onClick = { /* TODO: открыть приложение, если установлено */ },
                modifier = modifier,
                shape = shape
            ) {
                Text("Открыть")
            }
        }

        TaskStatus.FAILED -> {
            Column(
                modifier = modifier
            ) {
                Text(
                    text = "Ошибка: ${downloadState.errorMessage ?: "Неизвестная ошибка"}",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
                Button(
                    onClick = onRetryClick,
                    modifier = Modifier.fillMaxWidth(),
                    shape = shape
                ) {
                    Text("Повторить")
                }
            }
        }
    }
}