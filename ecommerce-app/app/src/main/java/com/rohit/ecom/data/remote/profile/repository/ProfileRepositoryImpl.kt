package com.rohit.ecom.data.remote.profile.repository

import com.rohit.ecom.data.remote.profile.ProfileApi
import com.rohit.ecom.data.remote.profile.dto.ProfileResponse
import com.rohit.ecom.data.remote.profile.dto.ProfileUpdateDto
import com.rohit.ecom.domain.profile.repository.ProfileRepository

class ProfileRepositoryImpl(
    private val profileApi: ProfileApi
) : ProfileRepository{
    override suspend fun updateProfile(updateProfileRequest: ProfileUpdateDto): ProfileResponse {
        return profileApi.updateProfile(updateProfileRequest)
    }

    override suspend fun getProfile(): ProfileResponse {
        return profileApi.getProfile()
    }


}