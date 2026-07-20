package com.aidarsarvartdinov.rustore.ui.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aidarsarvartdinov.rustore.data.models.ApiResult
import com.aidarsarvartdinov.rustore.data.models.Category
import com.aidarsarvartdinov.rustore.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val repository: AppRepository
): ViewModel() {
    private val _uiState = MutableStateFlow<ApiResult<List<Category>>>(ApiResult.Loading)
    val uiState: StateFlow<ApiResult<List<Category>>> = _uiState

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    init {
        loadCategories()
    }

    private suspend fun fetchCategories() {
        _uiState.value = repository.getCategories()
    }

    fun loadCategories() {
        viewModelScope.launch {
            _uiState.value = ApiResult.Loading
            fetchCategories()
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.value = true
            fetchCategories()
            _isRefreshing.value = false
        }
    }
}
