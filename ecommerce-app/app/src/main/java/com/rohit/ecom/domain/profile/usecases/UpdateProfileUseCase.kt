package com.rohit.ecom.domain.profile.usecases

import com.rohit.ecom.data.remote.profile.dto.ProfileResponse
import com.rohit.ecom.data.remote.profile.dto.ProfileUpdateDto
import com.rohit.ecom.domain.profile.repository.ProfileRepository
import com.rohit.ecom.utils.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UpdateProfileUseCase(
    private val profileRepository: ProfileRepository
) {

    operator fun invoke(updateProfileRequest: ProfileUpdateDto) : Flow<ApiResult<ProfileResponse>> = flow {

        emit(ApiResult.Loading)

        try {

            val getProfileResponse = profileRepository.updateProfile(updateProfileRequest)

            emit(ApiResult.Success(getProfileResponse))

        }catch (e: Exception){
            emit(
                ApiResult.Error(
                    message = e.message ?: "Something went wrong",
                    cause = e
                )
            )
        }

    }

}