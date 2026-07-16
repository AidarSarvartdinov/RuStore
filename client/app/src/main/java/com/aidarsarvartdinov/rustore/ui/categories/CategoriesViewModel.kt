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

    init {
        loadCategories()
    }

    fun loadCategories() {
        viewModelScope.launch {
            _uiState.value = ApiResult.Loading
            val result = repository.getCategories()
            _uiState.value = result
        }
    }
}
