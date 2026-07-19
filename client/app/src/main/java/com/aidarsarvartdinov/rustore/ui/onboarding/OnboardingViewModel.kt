package com.aidarsarvartdinov.rustore.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aidarsarvartdinov.rustore.data.datastore.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    private val _uiState = MutableStateFlow<Boolean?>(null)
    val uiState: StateFlow<Boolean?> = _uiState

    init {
        viewModelScope.launch {
            dataStoreManager.isOnboardingShown.collect { shown ->
                _uiState.value = shown
            }
        }
    }

    suspend fun completeOnboarding() {
        dataStoreManager.setOnboardingShown()
    }
}