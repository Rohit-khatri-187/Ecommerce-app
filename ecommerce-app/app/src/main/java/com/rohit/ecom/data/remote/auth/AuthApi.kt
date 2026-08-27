package com.rohit.ecom.data.remote.auth

import com.rohit.ecom.data.remote.auth.dto.LoginRequestDto
import com.rohit.ecom.data.remote.auth.dto.LoginResponseDto
import com.rohit.ecom.data.remote.auth.dto.RegisterRequestDto
import com.rohit.ecom.data.remote.auth.dto.RegisterResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("register")
    suspend fun register(
        @Body registerRequestDto: RegisterRequestDto
    ): RegisterResponseDto


    @POST("login")
    suspend fun login(
        @Body loginRequestDto: LoginRequestDto
    ): LoginResponseDto

}