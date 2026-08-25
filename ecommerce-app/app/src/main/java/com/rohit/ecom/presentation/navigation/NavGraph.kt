package com.rohit.ecom.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.rohit.ecom.presentation.screens.detailsscreen.DetailsScreen
import com.rohit.ecom.presentation.screens.homescreen.HomeScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.HomeScreen
    ) {
        composable<Route.HomeScreen> {
            HomeScreen(
                onProductClick = { productId ->
                    navController.navigate(Route.DetailsScreen(productId))
                }
            )
        }

        composable<Route.DetailsScreen> { backStackEntry ->
            val route: Route.DetailsScreen = backStackEntry.toRoute()
            DetailsScreen(
                productId = route.productId,
                onBackClick = {
                    navController.navigateUp()
                }
            )
        }
    }
}
