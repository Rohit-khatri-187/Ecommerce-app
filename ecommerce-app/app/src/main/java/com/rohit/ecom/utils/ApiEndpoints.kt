package com.rohit.ecom.utils

object ApiEndpoints {
    
    // Public Endpoints (No Token Required)
    val PUBLIC_ENDPOINTS = listOf(
        "auth/login",
        "auth/register",
        "products"
    )

    // Authenticated Endpoints (Token Required)
    val AUTHENTICATED_ENDPOINTS = listOf(
        "carts"
    )
}
