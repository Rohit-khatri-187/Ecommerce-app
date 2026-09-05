package com.rohit.ecommerce_backend.controllers;

import com.rohit.ecommerce_backend.dto.*;
import com.rohit.ecommerce_backend.service.AuthService;
import com.rohit.ecommerce_backend.utils.BaseController;
import com.rohit.ecommerce_backend.utils.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Auth endpoints : " , description = "These are the endpoints for the Auth ( login & register ) :  ")
@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController extends BaseController {

    private final AuthService authService;

    @Operation(
            summary = "Register User",
            description = "User have to first register then login "
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @PostMapping("/register")
    public ResponseEntity<BaseResponse<RegisterResponseDto>> register(
            @RequestBody RegisterRequestDto requestDto
            ){

        RegisterResponseDto response = authService.register(requestDto);

        return buildResponse(response,"Account created successfully");

    }

    @Operation(
            summary = "Login User",
            description = "User logins to this endpoints and gets the refresh token for the auth "
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @PostMapping("/login")
    public ResponseEntity<BaseResponse<LoginResponseDto>> login(
            @RequestBody LoginRequestDto requestDto
    ){

        LoginResponseDto response = authService.login(requestDto);

        return buildResponse(response,"Successful Login");

    }



}
