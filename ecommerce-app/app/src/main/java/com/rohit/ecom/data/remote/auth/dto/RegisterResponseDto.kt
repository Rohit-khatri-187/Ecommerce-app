package com.rohit.ecom.data.remote.auth.dto

data class RegisterResponseDto(
    val content: RegisterData,
    val message: String,
    val success: Boolean
)