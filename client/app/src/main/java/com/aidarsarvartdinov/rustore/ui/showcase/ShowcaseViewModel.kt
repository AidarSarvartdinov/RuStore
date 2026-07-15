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
    private val category: String? = savedStateHandle["category"]

    private val _uiState = MutableStateFlow<ApiResult<List<AppSummary>>>(ApiResult.Loading)
    val uiState: StateFlow<ApiResult<List<AppSummary>>> = _uiState

    init {
        loadApps()
    }

    fun loadApps() {
        viewModelScope.launch {
            _uiState.value = ApiResult.Loading
            val result = if (category != null) {
                repository.getAppsByCategory(category)
            } else {
                repository.getApps()
            }
            _uiState.value = result
        }
    }
}