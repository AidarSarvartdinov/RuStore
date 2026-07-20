package com.aidarsarvartdinov.rustore.ui.search

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
class SearchViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _uiState = MutableStateFlow<ApiResult<List<AppSummary>>>(ApiResult.Loading)
    val uiState: StateFlow<ApiResult<List<AppSummary>>> = _uiState

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    private var allApps: List<AppSummary> = emptyList()

    init {
        loadAllApps()
    }

    private suspend fun fetchData(query: String) {
        val list = if (query.isEmpty()) {
            allApps.sortedByDescending { it.downloads }.take(10)
        } else {
            val filtered = allApps.filter { it.name.contains(query, ignoreCase = true) }

            if (filtered.isEmpty()) {
                allApps.sortedByDescending { it.downloads }.take(10)
            } else {
                filtered
            }
        }

        _uiState.value = ApiResult.Success(list)
    }

    fun loadAllApps() {
        viewModelScope.launch {
            _uiState.value = ApiResult.Loading

            val result = repository.getApps()

            if (result is ApiResult.Success) {
                allApps = result.data
                fetchData(_searchQuery.value)
            } else {
                _uiState.value = result
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun performSearch() {
        viewModelScope.launch {
            _uiState.value = ApiResult.Loading
            fetchData(_searchQuery.value.trim())
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.value = true

            val result = repository.getApps()

            if (result is ApiResult.Success) {
                allApps = result.data
                fetchData(_searchQuery.value)
            } else {
                _uiState.value = result
            }

            _isRefreshing.value = false
        }
    }
}