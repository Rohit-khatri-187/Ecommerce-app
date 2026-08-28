package com.rohit.ecom.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Route {

    @Serializable
    data object HomeScreen : Route()

    @Serializable
    data class DetailsScreen(val productId: Int) : Route()

    @Serializable
    data object LoginScreen : Route()

    @Serializable
    data object SignUpScreen : Route()

    @Serializable
    data object CartScreen : Route()

}