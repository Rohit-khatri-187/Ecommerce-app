package com.rohit.ecom.data.remote.cart.dto

data class CartItem(
    val createdDate: String,
    val id: Int,
    val imageUrl: String,
    val name: String,
    val quantity: Int,
    val totalAmount: Int,
    val updatedDate: String
)