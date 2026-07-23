package com.aidarsarvartdinov.rustore.ui.appDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
    isInstalled: Boolean,
    onDownloadClick: () -> Unit,
    onCancelClick: () -> Unit,
    onRetryClick: () -> Unit,
    onOpenClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = ButtonDefaults.shape
) {
    when {
        isInstalled -> {
            Row(
                modifier = modifier,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onOpenClick,
                    modifier = Modifier.weight(1f),
                    shape = shape
                ) {
                    Text("Открыть")
                }
                Button(
                    onClick = onDeleteClick,
                    modifier = Modifier.weight(1f),
                    shape = shape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("Удалить")
                }
            }
        }
        downloadState?.status == TaskStatus.CANCELLED || downloadState == null -> {
            Button(
                onClick = onDownloadClick,
                modifier = modifier,
                shape = shape
            ) {
                Text("Установить")
            }
        }
        downloadState.status == TaskStatus.PENDING || downloadState.status == TaskStatus.IN_PROGRESS -> {
            Column(modifier = modifier) {
                Button(
                    onClick = { /* disabled */ },
                    modifier = Modifier.fillMaxWidth(),
                    shape = shape,
                    enabled = false
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.weight(1f),
                        color = Color.White,
                        strokeWidth = 2.dp
                    )
                    Text(" ${downloadState.progress}%", modifier = Modifier.weight(1f))
                }
                Button(
                    onClick = onCancelClick,
                    modifier = Modifier.fillMaxWidth(),
                    shape = shape,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Отменить")
                }
            }
        }
        downloadState.status == TaskStatus.COMPLETED -> {
            Row(
                modifier = modifier,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(onClick = onOpenClick, modifier = Modifier.weight(1f)) {
                    Text("Открыть")
                }
                Button(onClick = onDeleteClick, modifier = Modifier.weight(1f), colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)) {
                    Text("Удалить")
                }
            }
        }
        downloadState.status == TaskStatus.FAILED -> {
            Column(modifier = modifier) {
                Text("Ошибка: ${downloadState.errorMessage}", color = MaterialTheme.colorScheme.error)
                Button(onClick = onRetryClick, modifier = Modifier.fillMaxWidth()) {
                    Text("Повторить")
                }
            }
        }
    }
}