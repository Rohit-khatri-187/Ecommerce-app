package com.rohit.ecom.data.remote.cart.dto

data class AddToCartResponseDto(
    val content: CartItem,
    val message: String,
    val success: Boolean
)

