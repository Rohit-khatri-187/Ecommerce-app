package com.rohit.ecom.data.remote.auth.repository

import com.rohit.ecom.data.remote.auth.AuthApi
import com.rohit.ecom.data.remote.auth.dto.LoginRequestDto
import com.rohit.ecom.data.remote.auth.dto.LoginResponseDto
import com.rohit.ecom.data.remote.auth.dto.RegisterRequestDto
import com.rohit.ecom.data.remote.auth.dto.RegisterResponseDto
import com.rohit.ecom.domain.auth.repository.AuthRepository

class AuthRepositoryImpl(
    private val authApi : AuthApi
) : AuthRepository {
    override suspend fun register(registerRequestDto: RegisterRequestDto): RegisterResponseDto {
        return authApi.register(registerRequestDto);

    }

    override suspend fun login(loginRequestDto: LoginRequestDto): LoginResponseDto {
        return authApi.login(loginRequestDto)
    }


}