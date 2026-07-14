package com.aidarsarvartdinov.rustore.data.repository

import com.aidarsarvartdinov.rustore.data.models.AppDetails
import com.aidarsarvartdinov.rustore.data.models.AppSummary

interface AppRepository {
    suspend fun getApps(): List<AppSummary>
    suspend fun getAppDetails(appId: String): AppDetails
}
