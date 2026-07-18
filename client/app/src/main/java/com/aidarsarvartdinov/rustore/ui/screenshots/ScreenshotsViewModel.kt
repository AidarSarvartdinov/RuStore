package com.aidarsarvartdinov.rustore.ui.screenshots

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aidarsarvartdinov.rustore.data.models.ApiResult
import com.aidarsarvartdinov.rustore.data.models.AppDetails
import com.aidarsarvartdinov.rustore.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScreenshotsViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ApiResult<AppDetails>>(ApiResult.Loading)
    val uiState: StateFlow<ApiResult<AppDetails>> = _uiState

    fun loadScreenshots(appId: String) {
        viewModelScope.launch {
            _uiState.value = ApiResult.Loading
            val result = repository.getAppDetails(appId)
            _uiState.value = result
        }
    }
}
