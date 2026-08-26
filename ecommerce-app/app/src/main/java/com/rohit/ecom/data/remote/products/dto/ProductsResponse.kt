package com.rohit.ecom.data.remote.products.dto

data class ProductsResponse(
    val content: List<Product>,
    val isLast: Boolean,
    val pageNumber: Int,
    val pageSize: Int,
    val totalElements: Int,
    val totalPages: Int
)