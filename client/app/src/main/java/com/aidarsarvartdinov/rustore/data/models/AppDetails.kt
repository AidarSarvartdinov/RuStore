package com.aidarsarvartdinov.rustore.data.models

data class AppDetails(
    val id: String,
    val name: String,
    val description: String,
    val category: String,
    val developer: String,
    val ageRating: String,
    val iconUrl: String,
    val screenshots: List<String>,
    val downloads: Int = 0
)
