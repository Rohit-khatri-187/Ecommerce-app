package com.rohit.ecommerce_backend.service.impl;

import com.rohit.ecommerce_backend.dto.LoginRequestDto;
import com.rohit.ecommerce_backend.dto.LoginResponseDto;
import com.rohit.ecommerce_backend.dto.RegisterRequestDto;
import com.rohit.ecommerce_backend.dto.RegisterResponseDto;
import com.rohit.ecommerce_backend.entities.User;
import com.rohit.ecommerce_backend.enums.ROLE;
import com.rohit.ecommerce_backend.exceptions.InvalidCredentialException;
import com.rohit.ecommerce_backend.exceptions.NotFoundException;
import com.rohit.ecommerce_backend.mappers.UserMapper;
import com.rohit.ecommerce_backend.metrics.UserMetrics;
import com.rohit.ecommerce_backend.repositories.UserRepository;
import com.rohit.ecommerce_backend.security.CustomUserDetail;
import com.rohit.ecommerce_backend.security.JwtUtils;
import com.rohit.ecommerce_backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserMetrics userMetrics;

    @Override
    public RegisterResponseDto register(RegisterRequestDto request) {

        log.info("Inside the register");

        if(userRepository.existsByEmail(request.getEmail())){
            throw new NotFoundException("Email already exists");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(ROLE.USER)
                .build();

        User savedUser = userRepository.save(user);

        userMetrics.incrementRegisteredUsers();

        return userMapper.toRegisterResponseDto(savedUser);
    }

    @Override
    public LoginResponseDto login(LoginRequestDto request) {

        log.info("Inside the login()");

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
            User user = customUserDetail.getUser();
            String token = jwtUtils.generateToken(user.getEmail());

            return LoginResponseDto.builder()
                    .token(token)
                    .role(user.getRole())
                    .build();

        } catch (AuthenticationException e) {
            throw new InvalidCredentialException("Invalid email or password");
        }

    }
}
