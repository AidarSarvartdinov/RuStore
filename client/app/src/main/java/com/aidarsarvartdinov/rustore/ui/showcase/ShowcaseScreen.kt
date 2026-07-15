package com.aidarsarvartdinov.rustore.ui.showcase

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.aidarsarvartdinov.rustore.data.models.ApiResult

@Composable
fun ShowcaseScreen(
    viewModel: ShowcaseViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is ApiResult.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is ApiResult.Success -> {
            val apps = (uiState as ApiResult.Success).data
            LazyColumn(modifier = Modifier.padding(top = 20.dp)) {
                items(apps) { app ->
                    AppCard(app) { }
                }
            }
        }
        is ApiResult.Error -> {
            val error = (uiState as ApiResult.Error).message
            Text(text = error)
//            ErrorScreen(message = error) {
//                viewModel.loadApps() //retry
//            }
        }
    }
}