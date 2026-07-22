package com.aidarsarvartdinov.rustore.data.repository

import com.aidarsarvartdinov.rustore.data.models.ApiResult
import com.aidarsarvartdinov.rustore.data.models.AppDetails
import com.aidarsarvartdinov.rustore.data.models.AppSummary
import com.aidarsarvartdinov.rustore.data.models.Category
import com.aidarsarvartdinov.rustore.data.network.AppApi
import javax.inject.Inject

class AppNetworkRepository @Inject constructor(
    private val api: AppApi
): AppRepository {
    override suspend fun getApps(): ApiResult<List<AppSummary>> =
        try { ApiResult.Success(api.getApps()) }
        catch (e: Exception) { ApiResult.Error(e.message ?: "Ошибка загрузки") }

    override suspend fun getAppsByCategory(categoryName: String): ApiResult<List<AppSummary>> =
        try { ApiResult.Success(api.getApps(categoryName)) }
        catch (e: Exception) { ApiResult.Error(e.message ?: "Ошибка фильтрации") }

    override suspend fun getAppDetails(appId: String): ApiResult<AppDetails> =
        try { ApiResult.Success(api.getAppDetails(appId)) }
        catch (e: Exception) { ApiResult.Error(e.message ?: "Ошибка загрузки деталей") }

    override suspend fun getCategories(): ApiResult<List<Category>> =
        try { ApiResult.Success(api.getCategories()) }
        catch (e: Exception) { ApiResult.Error(e.message ?: "Ошибка загрузки категорий") }

    override suspend fun searchApps(query: String): ApiResult<List<AppSummary>> =
        try { ApiResult.Success(api.searchApps(query)) }
        catch (e: Exception) { ApiResult.Error(e.message ?: "Ошибка поиска") }
}