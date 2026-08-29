package com.rohit.ecom.data.remote.profile.dto

data class ProfileResponse(
    val content: ProfileData,
    val message: String,
    val success: Boolean
)