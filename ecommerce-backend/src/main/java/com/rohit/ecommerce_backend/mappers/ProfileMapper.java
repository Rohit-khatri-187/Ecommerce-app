package com.rohit.ecommerce_backend.mappers;

import com.rohit.ecommerce_backend.dto.ProfileDto;
import com.rohit.ecommerce_backend.entities.User;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {

    public ProfileDto mapToProfileDto(User user) {
        return ProfileDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .mobileNumber(user.getMobileNumber())
                .address(user.getAddress())
                .createdAt(user.getCreatedDate())
                .updatedAt(user.getUpdatedDate())
                .build();
    }

}
