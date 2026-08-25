package com.rohit.ecom.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Route {

    @Serializable
    data object HomeScreen : Route()

    @Serializable
    data class DetailsScreen(val productId: Int) : Route()
}