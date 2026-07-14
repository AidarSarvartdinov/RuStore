package com.aidarsarvartdinov.rustore.data.repository

import com.aidarsarvartdinov.rustore.data.models.AppDetails
import com.aidarsarvartdinov.rustore.data.models.AppSummary
import com.aidarsarvartdinov.rustore.data.models.Category

interface AppRepository {
    suspend fun getApps(): List<AppSummary>
    suspend fun getAppsByCategory(categoryName: String): List<AppSummary>
    suspend fun getAppDetails(appId: String): AppDetails
    suspend fun getCategories(): Category
}
