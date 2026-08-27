package com.rohit.ecommerce_backend.controllers;

import com.rohit.ecommerce_backend.dto.ProfileDto;
import com.rohit.ecommerce_backend.dto.ProfileUpdateDto;
import com.rohit.ecommerce_backend.service.ProfileService;
import com.rohit.ecommerce_backend.utils.BaseController;
import com.rohit.ecommerce_backend.utils.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RequestMapping("/profile")
@RestController
public class ProfileController extends BaseController {

    private final ProfileService profileService;

    @PostMapping
    public ResponseEntity<BaseResponse<ProfileDto>> updateProfile(@RequestBody ProfileUpdateDto updateDto){

        ProfileDto updatedProfile = profileService.updateProfile(updateDto);

        return buildResponse(updatedProfile,"Profile updated sucessfully");
    }

    @GetMapping
    public ResponseEntity<BaseResponse<ProfileDto>> getProfile(){

        ProfileDto profile = profileService.getProfile();
        return buildResponse(profile);
    }


}
