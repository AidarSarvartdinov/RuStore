package com.aidarsarvartdinov.rustore.ui.appDetails

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aidarsarvartdinov.rustore.data.models.ApiResult
import com.aidarsarvartdinov.rustore.data.models.AppDetails
import com.aidarsarvartdinov.rustore.data.network.models.TaskStatus
import com.aidarsarvartdinov.rustore.data.repository.AppRepository
import com.aidarsarvartdinov.rustore.data.repository.DownloadProgress
import com.aidarsarvartdinov.rustore.data.repository.DownloadRepository
import com.aidarsarvartdinov.rustore.data.repository.DownloadStateRepository
import com.aidarsarvartdinov.rustore.data.repository.InstalledAppsRepository
import com.aidarsarvartdinov.rustore.utils.ApkInstaller
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    private val repository: AppRepository,
    private val downloadRepository: DownloadRepository,
    private val installedAppsRepository: InstalledAppsRepository,
    private val downloadStateRepository: DownloadStateRepository,
    private val apkInstaller: ApkInstaller,
    private val savedStateHandle: SavedStateHandle,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _uiState = MutableStateFlow<ApiResult<AppDetails>>(ApiResult.Loading)
    val uiState: StateFlow<ApiResult<AppDetails>> = _uiState

    private val _downloadState = MutableStateFlow<DownloadProgress?>(null)
    val downloadState: StateFlow<DownloadProgress?> = _downloadState

    private val _isInstalled = MutableStateFlow(false)
    val isInstalled: StateFlow<Boolean> = _isInstalled

    private val _apkFilePath = MutableStateFlow<String?>(null)
    val apkFilePath: StateFlow<String?> = _apkFilePath

    private var downloadJob: Job? = null
    private val appId: String = savedStateHandle["appId"] ?: ""

    init {
        loadApp()
        checkIfInstalled()
        restoreApkPath()
        restoreTaskId()
    }

    private fun restoreApkPath() {
        viewModelScope.launch {
            downloadStateRepository.getApkPath(appId).collect { path ->
                if (path != null && File(path).exists()) {
                    _apkFilePath.value = path
                    _isInstalled.value = true
                } else {
                    downloadStateRepository.removeApkPath(appId)
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun restoreTaskId() {
        viewModelScope.launch {
            downloadStateRepository.getTaskId(appId)
                .flatMapLatest { taskId ->
                    if (taskId != null) {
                        downloadRepository.getProgress(taskId)
                    } else {
                        flowOf(null)
                    }
                }
                .collect { progress ->
                    if (progress == null) return@collect
                    _downloadState.value = progress
                    when (progress.status) {
                        TaskStatus.COMPLETED -> {
                            if (!_isInstalled.value && progress.apkFile != null) {
                                handleCompleted(progress.apkFile, progress.taskId)
                            } else {
                                downloadStateRepository.removeTaskId(appId)
                            }
                        }
                        TaskStatus.FAILED, TaskStatus.CANCELLED -> {
                            downloadStateRepository.removeTaskId(appId)
                            _downloadState.value = null
                        }
                        else -> { /* continue collecting */ }
                    }
                }
        }
    }

    private fun checkIfInstalled() {
        viewModelScope.launch {
            installedAppsRepository.isAppInstalled(appId).collect { installed ->
                _isInstalled.value = installed
            }
        }
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
                if (progress.taskId.isNotEmpty()) {
                    downloadStateRepository.saveTaskId(appId, progress.taskId)
                }
                _downloadState.value = progress
                if (progress.status == TaskStatus.COMPLETED && progress.apkFile != null) {
                    handleCompleted(progress.apkFile, progress.taskId)
                }
            }
        }
    }

    private suspend fun handleCompleted(apkFile: File, taskId: String) {
        val success = apkInstaller.installApk(apkFile)

        if (success) {
            installedAppsRepository.addInstalledApp(appId)
            _isInstalled.value = true
            _apkFilePath.value = apkFile.absolutePath
            downloadStateRepository.saveApkPath(appId, apkFile.absolutePath)
        } else {
            _downloadState.value = DownloadProgress(
                taskId,
                status = TaskStatus.FAILED,
                errorMessage = "Не удалось установить приложение"
            )
        }

        downloadStateRepository.removeTaskId(appId)
    }

    fun cancelDownload() {
        downloadJob?.cancel()

        val taskId = _downloadState.value?.taskId
        if (taskId != null) {
            viewModelScope.launch {
                try {
                    downloadRepository.cancelDownload(taskId)
                    downloadStateRepository.removeTaskId(appId)
                } catch (e: Exception) { /* ignore */ }
            }
        }

        _downloadState.value = null
    }

    fun deleteApp() {
        viewModelScope.launch {
            installedAppsRepository.removeInstalledApp(appId)
            _isInstalled.value = false

            _apkFilePath.value?.let { path ->
                File(path).delete()
                _apkFilePath.value = null
                downloadStateRepository.removeApkPath(appId)
            }

            downloadStateRepository.removeTaskId(appId)
            _downloadState.value = null
            downloadJob?.cancel()
        }
    }

    override fun onCleared() {
        downloadJob?.cancel()
    }
}