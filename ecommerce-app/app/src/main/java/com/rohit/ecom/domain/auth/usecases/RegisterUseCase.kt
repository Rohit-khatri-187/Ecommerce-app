package com.rohit.ecom.domain.auth.usecases

import com.rohit.ecom.data.remote.auth.dto.RegisterRequestDto
import com.rohit.ecom.data.remote.auth.dto.RegisterResponseDto
import com.rohit.ecom.domain.auth.repository.AuthRepository
import com.rohit.ecom.utils.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RegisterUseCase(
    private val authRepository: AuthRepository
) {

    operator fun invoke(registerRequestDto: RegisterRequestDto): Flow<ApiResult<RegisterResponseDto>> = flow {
        emit(ApiResult.Loading)

        try {

            val registerResponse = authRepository.register(registerRequestDto)

            emit(ApiResult.Success(registerResponse))

        }catch (e: Exception ){
            emit(
                ApiResult.Error(
                    message = e.message ?: "Something went wrong",
                    cause = e
                )
            )
        }


    }

}