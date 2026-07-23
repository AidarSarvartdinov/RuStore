package com.aidarsarvartdinov.rustore.data.network

import com.aidarsarvartdinov.rustore.data.network.models.TaskResponse
import com.aidarsarvartdinov.rustore.data.network.models.TaskStatusResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Streaming

interface DownloadApi {
    @POST("api/download/start")
    suspend fun startDownload(@Query("appId") appId: String): TaskResponse

    @GET("api/download/status/{taskId}")
    suspend fun getTaskStatus(@Path("taskId") taskId: String): TaskStatusResponse

    @DELETE("api/download/cancel/{taskId}")
    suspend fun cancelTask(@Path("taskId") taskId: String): Response<Unit>

    @GET("api/download/file/{taskId}")
    @Streaming
    suspend fun downloadFile(@Path("taskId") taskId: String): Response<ResponseBody>
}