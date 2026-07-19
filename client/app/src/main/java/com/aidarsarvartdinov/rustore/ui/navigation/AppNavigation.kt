package com.aidarsarvartdinov.rustore.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aidarsarvartdinov.rustore.ui.appDetails.AppDetailsScreen
import com.aidarsarvartdinov.rustore.ui.categories.CategoriesScreen
import com.aidarsarvartdinov.rustore.ui.screenshots.ScreenshotsScreen
import com.aidarsarvartdinov.rustore.ui.search.SearchScreen
import com.aidarsarvartdinov.rustore.ui.showcase.ShowcaseScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "showcase") {
        composable(
            "showcase?category={category}",
            arguments = listOf(navArgument("category") {
                type = NavType.StringType
                nullable = true
                defaultValue = null }),
        ) { backStackEntry ->
            ShowcaseScreen(navController)
        }
        composable("categories") {
            CategoriesScreen(navController)
        }
        composable(
            route = "appDetails/{appId}",
            arguments = listOf(
                navArgument("appId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val appId = backStackEntry.arguments?.getString("appId") ?: ""
            AppDetailsScreen(
                navController = navController,
                appId = appId
            )
        }
        composable(
            route = "screenshots/{appId}/{selectedIndex}",
            arguments = listOf(
                navArgument("appId") { type = NavType.StringType },
                navArgument("selectedIndex") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val appId = backStackEntry.arguments?.getString("appId") ?: ""
            val selectedIndex = backStackEntry.arguments?.getInt("selectedIndex") ?: 0
            ScreenshotsScreen(
                navController = navController,
                appId = appId,
                selectedIndex = selectedIndex
            )
        }
        composable("search") {
            SearchScreen(navController)
        }
    }
}
