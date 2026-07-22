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


    init {
        performSearch()
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun performSearch() {
        viewModelScope.launch {
            _uiState.value = ApiResult.Loading
            val result = repository.searchApps(_searchQuery.value.trim())
            _uiState.value = result
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.value = true
            val result = repository.searchApps(_searchQuery.value.trim())
            _uiState.value = result
            _isRefreshing.value = false
        }
    }
}