package com.aidarsarvartdinov.rustore.ui.showcase

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.aidarsarvartdinov.rustore.data.models.ApiResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowcaseScreen(
    navController: NavController,
    viewModel: ShowcaseViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val category: String? = viewModel.category

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (category != null) "Приложения: $category" else "Приложения"
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                ),
                modifier = Modifier.shadow(4.dp)
            )
        },

        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    TextButton(
                        onClick = { navController.navigate("categories") }
                    ) {

                        Icon(Icons.AutoMirrored.Filled.List, contentDescription = null)
                        Spacer(Modifier.width(4.dp))
                        Text("Категории")
                    }

                    TextButton(
                        onClick = {
                            TODO("Navigate to search")
                        }
                    ) {
                        Icon(Icons.Default.Search, contentDescription = null)
                        Spacer(Modifier.width(4.dp))
                        Text("Поиск")
                    }
                }
            }
        }
    ) { innerPadding ->
        when (uiState) {
            is ApiResult.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is ApiResult.Success -> {
                val apps = (uiState as ApiResult.Success).data
                LazyColumn(
                        modifier = Modifier.padding(innerPadding)
                ) {
                    items(apps) { app ->
                        AppCard(app) {
                            navController.navigate("appDetails/${app.id}")
                        }
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
}