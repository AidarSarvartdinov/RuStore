package com.aidarsarvartdinov.rustore.ui.appDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.aidarsarvartdinov.rustore.data.models.ApiResult
import com.aidarsarvartdinov.rustore.ui.common.ErrorScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDetailsScreen(
    navController: NavController,
    appId: String,
    viewModel: AppDetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(appId) {
        viewModel.loadApp(appId)
    }

    Scaffold(
        topBar = {

            Box(modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.surface)

            ) {
                IconButton(
                    onClick = { navController.navigateUp() },
                    modifier = Modifier.padding(top = 24.dp)
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Назад",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (uiState) {
                is ApiResult.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is ApiResult.Success -> {
                    val app = (uiState as ApiResult.Success).data
                    AppDetailsContent(app = app, navController = navController)
                }
                is ApiResult.Error -> {
                    val error = (uiState as ApiResult.Error).message
                    ErrorScreen(message = error) {
                        viewModel.loadApp(appId)
                    }
                }
            }
        }
    }
}