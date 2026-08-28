package com.rohit.ecom.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.rohit.ecom.presentation.screens.cartScreen.CartScreen
import com.rohit.ecom.presentation.screens.detailsscreen.DetailsScreen
import com.rohit.ecom.presentation.screens.homescreen.HomeScreen
import com.rohit.ecom.presentation.screens.loginscreen.LoginScreen
import com.rohit.ecom.presentation.screens.signupscreen.SignUpScreen

@Composable
fun NavGraph(
    startDestination: Route
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        
        composable<Route.SignUpScreen>{
            SignUpScreen(
                onHaveAnAccountClick = {
                    navController.navigate(Route.LoginScreen)
                },
                onSuccessfulSignUp = {
                    navController.navigate(Route.LoginScreen)
                }
            )
        }

        composable<Route.LoginScreen>{
            LoginScreen(
                onDontHaveAnAccountClick = {
                    navController.navigate(Route.SignUpScreen)
                },
                onSuccessfulLogin = {
                    navController.navigate(Route.HomeScreen) {
                        popUpTo(Route.LoginScreen) { inclusive = true }
                    }
                }
            )
        }
        
        composable<Route.HomeScreen> {
            HomeScreen(
                onProductClick = { productId ->
                    navController.navigate(Route.DetailsScreen(productId))
                },
                onCartClick = {
                    navController.navigate(Route.CartScreen)
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

        composable<Route.CartScreen> {
            CartScreen(
                onBackClick = {
                    navController.navigateUp()
                },
                onShopNowClick = {
                    navController.navigate(Route.HomeScreen) {
                        popUpTo(Route.CartScreen) { inclusive = true }
                    }
                }
            )
        }
    }
}
