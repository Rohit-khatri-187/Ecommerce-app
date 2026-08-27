package com.rohit.ecommerce_backend.service;

import com.rohit.ecommerce_backend.dto.ProfileDto;
import com.rohit.ecommerce_backend.dto.ProfileUpdateDto;

public interface ProfileService {

    ProfileDto updateProfile(ProfileUpdateDto profileDto);

    ProfileDto getProfile();

}
