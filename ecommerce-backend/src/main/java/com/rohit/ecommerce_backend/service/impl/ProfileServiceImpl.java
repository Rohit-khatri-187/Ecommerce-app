package com.rohit.ecommerce_backend.service.impl;

import com.rohit.ecommerce_backend.dto.ProfileDto;
import com.rohit.ecommerce_backend.dto.ProfileUpdateDto;
import com.rohit.ecommerce_backend.entities.User;
import com.rohit.ecommerce_backend.mappers.ProfileMapper;
import com.rohit.ecommerce_backend.repositories.UserRepository;
import com.rohit.ecommerce_backend.service.ProfileService;
import com.rohit.ecommerce_backend.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@RequiredArgsConstructor
@Service
public class ProfileServiceImpl implements ProfileService {

    private final SecurityUtils securityUtils;
    private final ProfileMapper profileMapper;
    private final UserRepository userRepository;

    @Override
    public ProfileDto updateProfile(ProfileUpdateDto profileDto) {

        log.info("Inside updateProfile : () ");

        User user = securityUtils.getCurrentUser();

        user.setMobileNumber(profileDto.getMobileNumber());
        user.setAddress(profileDto.getAddress());

        User updatedUser = userRepository.save(user);

        return profileMapper.mapToProfileDto(updatedUser);
    }

    @Override
    public ProfileDto getProfile() {
        log.info("Inside getProfile : ()  ");
        User user = securityUtils.getCurrentUser();
        return profileMapper.mapToProfileDto(user);
    }
}
