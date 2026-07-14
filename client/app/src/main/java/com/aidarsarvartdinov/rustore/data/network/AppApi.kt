package com.aidarsarvartdinov.rustore.data.network

import com.aidarsarvartdinov.rustore.data.models.AppDetails
import com.aidarsarvartdinov.rustore.data.models.AppSummary
import retrofit2.http.GET
import retrofit2.http.Path

interface AppApi {
    @GET("apps")
    suspend fun getApps(): List<AppSummary>

    @GET("/apps/{id}")
    suspend fun getAppDetails(@Path("id") appId: String): AppDetails
}