package com.rohit.ecom.data.remote.auth.dto

data class RegisterData(
    val createdDate: String,
    val email: String,
    val id: Int,
    val name: String,
    val role: String,
    val updatedDate: String
)