package com.aidarsarvartdinov.rustore.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aidarsarvartdinov.rustore.ui.categories.CategoriesScreen
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
    }
}
