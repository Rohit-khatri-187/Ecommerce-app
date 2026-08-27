package com.rohit.ecom.data.remote.auth.dto

data class LoginResponseDto(
    val content: LoginData,
    val message: String,
    val success: Boolean
)