package com.aidarsarvartdinov.server.rustore.dto

data class AppSummary(
    val id: String,
    val name: String,
    val shortDescription: String,
    val category: String,
    val iconUrl: String,
    val downloads: Int = 0
)
