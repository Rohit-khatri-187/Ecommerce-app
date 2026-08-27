package com.rohit.ecom.data.remote.auth.dto

data class RegisterRequestDto(
    val email: String,
    val name: String,
    val password: String
)