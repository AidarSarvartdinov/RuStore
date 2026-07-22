package com.aidarsarvartdinov.rustore.data.repository

import com.aidarsarvartdinov.rustore.data.models.ApiResult
import com.aidarsarvartdinov.rustore.data.models.AppDetails
import com.aidarsarvartdinov.rustore.data.models.AppSummary
import com.aidarsarvartdinov.rustore.data.models.Category

interface AppRepository {
    suspend fun getApps(): ApiResult<List<AppSummary>>
    suspend fun getAppsByCategory(categoryName: String): ApiResult<List<AppSummary>>
    suspend fun getAppDetails(appId: String): ApiResult<AppDetails>
    suspend fun searchApps(query: String): ApiResult<List<AppSummary>>
    suspend fun getCategories(): ApiResult<List<Category>>
}
