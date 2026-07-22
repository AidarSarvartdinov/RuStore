package com.aidarsarvartdinov.rustore.data.network

import com.aidarsarvartdinov.rustore.data.models.AppDetails
import com.aidarsarvartdinov.rustore.data.models.AppSummary
import com.aidarsarvartdinov.rustore.data.models.Category
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AppApi {

    @GET("api/apps")
    suspend fun getApps(@Query("category") categoryName: String? = null): List<AppSummary>

    @GET("api/apps/{id}")
    suspend fun getAppDetails(@Path("id") appId: String): AppDetails

    @GET("api/apps/search")
    suspend fun searchApps(@Query("query") query: String): List<AppSummary>

    @GET("api/apps/categories")
    suspend fun getCategories(): List<Category>
}
