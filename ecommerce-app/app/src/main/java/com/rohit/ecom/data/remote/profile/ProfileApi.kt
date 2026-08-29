package com.rohit.ecom.data.remote.profile

import com.rohit.ecom.data.remote.profile.dto.ProfileResponse
import com.rohit.ecom.data.remote.profile.dto.ProfileUpdateDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ProfileApi {

    @POST("profile")
    suspend fun updateProfile(
        @Body updateProfileRequest: ProfileUpdateDto
    ): ProfileResponse


    @GET("profile")
    suspend fun getProfile() : ProfileResponse

}