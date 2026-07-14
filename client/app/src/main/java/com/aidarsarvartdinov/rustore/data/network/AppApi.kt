package com.aidarsarvartdinov.rustore.data.network

import com.aidarsarvartdinov.rustore.data.models.AppDetails
import com.aidarsarvartdinov.rustore.data.models.AppSummary
import com.aidarsarvartdinov.rustore.data.models.Category
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AppApi {
    @GET("apps")
    suspend fun getApps(): List<AppSummary>

    @GET("apps")
    suspend fun getAppsByCategory(@Query("category") categoryName: String): List<AppSummary>

    @GET("/apps/{id}")
    suspend fun getAppDetails(@Path("id") appId: String): AppDetails

    @GET("categories")
    suspend fun getCategories(): List<Category>
}
