package com.rohit.ecommerce_backend.service;

import com.rohit.ecommerce_backend.dto.LoginRequestDto;
import com.rohit.ecommerce_backend.dto.LoginResponseDto;
import com.rohit.ecommerce_backend.dto.RegisterRequestDto;
import com.rohit.ecommerce_backend.dto.RegisterResponseDto;

public interface AuthService {

    RegisterResponseDto register(RegisterRequestDto request);

    LoginResponseDto login(LoginRequestDto request);

}
