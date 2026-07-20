package com.aidarsarvartdinov.rustore.ui.showcase

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aidarsarvartdinov.rustore.data.models.ApiResult
import com.aidarsarvartdinov.rustore.data.models.AppSummary
import com.aidarsarvartdinov.rustore.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowcaseViewModel @Inject constructor(
    private val repository: AppRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    val category: String? = savedStateHandle["category"]

    private val _uiState = MutableStateFlow<ApiResult<List<AppSummary>>>(ApiResult.Loading)
    val uiState: StateFlow<ApiResult<List<AppSummary>>> = _uiState

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    init {
        loadApps()
    }

    private suspend fun fetchApps() {
        val result = if (category != null) {
            repository.getAppsByCategory(category)
        } else {
            repository.getApps()
        }
        _uiState.value = result
    }

    fun loadApps() {
        viewModelScope.launch {
            _uiState.value = ApiResult.Loading
            fetchApps()
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.value = true
            fetchApps()
            _isRefreshing.value = false
        }
    }
}