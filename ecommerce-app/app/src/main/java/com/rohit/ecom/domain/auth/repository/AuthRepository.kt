package com.rohit.ecom.domain.auth.repository

import com.rohit.ecom.data.remote.auth.dto.LoginRequestDto
import com.rohit.ecom.data.remote.auth.dto.LoginResponseDto
import com.rohit.ecom.data.remote.auth.dto.RegisterRequestDto
import com.rohit.ecom.data.remote.auth.dto.RegisterResponseDto

interface AuthRepository {

    suspend fun register(registerRequestDto: RegisterRequestDto): RegisterResponseDto

    suspend fun login(loginRequestDto: LoginRequestDto): LoginResponseDto

}