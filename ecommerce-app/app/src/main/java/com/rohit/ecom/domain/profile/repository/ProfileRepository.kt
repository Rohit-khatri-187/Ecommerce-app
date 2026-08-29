package com.rohit.ecom.domain.profile.repository

import com.rohit.ecom.data.remote.profile.dto.ProfileResponse
import com.rohit.ecom.data.remote.profile.dto.ProfileUpdateDto

interface ProfileRepository {

    suspend fun updateProfile(updateProfileRequest: ProfileUpdateDto): ProfileResponse

    suspend fun getProfile(): ProfileResponse

}