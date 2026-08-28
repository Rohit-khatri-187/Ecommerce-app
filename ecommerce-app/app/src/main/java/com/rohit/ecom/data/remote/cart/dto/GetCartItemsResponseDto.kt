package com.rohit.ecom.data.remote.cart.dto

data class GetCartItemsResponseDto(
    val content: List<CartItem>,
    val message: String,
    val success: Boolean
)

