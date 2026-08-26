package com.rohit.ecommerce_backend.controllers;

import com.rohit.ecommerce_backend.dto.*;
import com.rohit.ecommerce_backend.service.AuthService;
import com.rohit.ecommerce_backend.utils.BaseController;
import com.rohit.ecommerce_backend.utils.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController extends BaseController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<BaseResponse<RegisterResponseDto>> register(
            @RequestBody RegisterRequestDto requestDto
            ){

        RegisterResponseDto response = authService.register(requestDto);

        return buildResponse(response,"Account created successfully");

    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<LoginResponseDto>> login(
            @RequestBody LoginRequestDto requestDto
    ){

        LoginResponseDto response = authService.login(requestDto);

        return buildResponse(response,"Successful Login");

    }



}
