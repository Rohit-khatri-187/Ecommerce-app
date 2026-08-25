package com.rohit.ecom.data.remote.dto

data class Product(
    val category: String,
    val createdDate: String,
    val description: String,
    val id: Int,
    val imageUrl: String,
    val name: String,
    val stock: Int,
    val updatedDate: String
)