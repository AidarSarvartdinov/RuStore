package com.aidarsarvartdinov.server.rustore.controller

import com.aidarsarvartdinov.server.rustore.dto.AppDetails
import com.aidarsarvartdinov.server.rustore.dto.AppSummary
import com.aidarsarvartdinov.server.rustore.dto.Category
import com.aidarsarvartdinov.server.rustore.service.AppService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/apps")
class AppController(private val appService: AppService) {

    @GetMapping
    fun getAppsByCategory(@RequestParam(required = false) category: String?): List<AppSummary> {
        return if (category != null) {
            appService.getAppsByCategroy(category)
        } else {
            appService.getApps()
        }
    }

    @GetMapping("/{id}")
    fun getAppDetails(@PathVariable id: String): AppDetails? = appService
        .getAppDetails(id)

    @GetMapping("/search")
    fun searchApps(@RequestParam("query") query: String): List<AppSummary> = appService
        .searchApps(query)

    @GetMapping("/categories")
    fun getCategories(): List<Category> = appService
        .getCategories()
}