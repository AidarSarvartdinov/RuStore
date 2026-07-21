package com.aidarsarvartdinov.rustore.ui.appDetails

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aidarsarvartdinov.rustore.data.models.ApiResult
import com.aidarsarvartdinov.rustore.data.models.AppDetails
import com.aidarsarvartdinov.rustore.data.network.models.TaskStatus
import com.aidarsarvartdinov.rustore.data.repository.AppRepository
import com.aidarsarvartdinov.rustore.data.repository.DownloadProgress
import com.aidarsarvartdinov.rustore.data.repository.DownloadRepository
import com.aidarsarvartdinov.rustore.utils.ApkInstaller
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    private val repository: AppRepository,
    private val downloadRepository: DownloadRepository,
    private val apkInstaller: ApkInstaller,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow<ApiResult<AppDetails>>(ApiResult.Loading)
    val uiState: StateFlow<ApiResult<AppDetails>> = _uiState

    private val _downloadState = MutableStateFlow<DownloadProgress?>(null)
    val downloadState: StateFlow<DownloadProgress?> = _downloadState

    private var downloadJob: Job? = null
    private val appId: String = savedStateHandle["appId"] ?: ""

    init {
        loadApp()
    }

    fun loadApp() {
        viewModelScope.launch {
            _uiState.value = ApiResult.Loading
            _uiState.value = repository.getAppDetails(appId)
        }
    }

    fun startDownload() {
        if (downloadJob?.isActive == true) return

        downloadJob = viewModelScope.launch {
            downloadRepository.downloadApp(appId).collect { progress ->
                _downloadState.value = progress

                if (progress.status == TaskStatus.COMPLETED && progress.downloadedFile != null) {
                    installApk(progress.downloadedFile)
                }
            }
        }
    }

    fun cancelDownload() {
        downloadJob?.cancel()

        val taskId = _downloadState.value?.taskId
        if (taskId != null) {
            viewModelScope.launch {
                try {
                    downloadRepository.cancelDownload(taskId)
                } catch (e: Exception) {
                    Log.e("AppDetailVM", "Error canceling download", e)
                }
            }
        }
    }

    private fun installApk(file: File) {
        val success = apkInstaller.installApk(file)
        if (!success) {
            _downloadState.value = _downloadState.value?.copy(
                status = TaskStatus.FAILED,
                errorMessage = "Не удалось установить приложение"
            )
        }
    }

    override fun onCleared() {
        downloadJob?.cancel()
    }
}