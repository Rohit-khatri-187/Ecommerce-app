package com.rohit.ecommerce_backend.mappers;

import com.rohit.ecommerce_backend.dto.RegisterResponseDto;
import com.rohit.ecommerce_backend.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public RegisterResponseDto toRegisterResponseDto(User user) {
        if (user == null) {
            return null;
        }

        return RegisterResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .createdDate(user.getCreatedDate())
                .updatedDate(user.getUpdatedDate())
                .build();
    }
}
