package com.rohit.ecom.domain.auth.usecases

import com.rohit.ecom.data.remote.auth.dto.LoginRequestDto
import com.rohit.ecom.data.remote.auth.dto.LoginResponseDto
import com.rohit.ecom.domain.auth.repository.AuthRepository
import com.rohit.ecom.domain.manager.tokenmanager.repository.TokenPref
import com.rohit.ecom.utils.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor (
    private val authRepository: AuthRepository,
    private val tokenPref: TokenPref
) {

    operator fun invoke(loginRequestDto: LoginRequestDto): Flow<ApiResult<LoginResponseDto>> = flow {
        emit(ApiResult.Loading)

        try {

            val loginResponse = authRepository.login(loginRequestDto)

            if(loginResponse.success){
                tokenPref.saveRefreshToken(loginResponse.content.token)
            }

            emit(ApiResult.Success(loginResponse))


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