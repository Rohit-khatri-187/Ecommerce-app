package com.rohit.ecommerce_backend.controllers;

import com.rohit.ecommerce_backend.dto.ProfileDto;
import com.rohit.ecommerce_backend.dto.ProfileUpdateDto;
import com.rohit.ecommerce_backend.service.ProfileService;
import com.rohit.ecommerce_backend.utils.BaseController;
import com.rohit.ecommerce_backend.utils.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Profile Endpoints : " , description = "These are endpoints to update the Profile and get profile data ")
@RequiredArgsConstructor
@RequestMapping("/profile")
@RestController
public class ProfileController extends BaseController {

    private final ProfileService profileService;

    @Operation(
            summary = "Update Profile",
            description = "User can update his profile"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @PostMapping
    public ResponseEntity<BaseResponse<ProfileDto>> updateProfile(@RequestBody ProfileUpdateDto updateDto){

        ProfileDto updatedProfile = profileService.updateProfile(updateDto);

        return buildResponse(updatedProfile,"Profile updated successfully");
    }

    @Operation(
            summary = "Fetch Profile",
            description = "Fetches the current user profile"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping
    public ResponseEntity<BaseResponse<ProfileDto>> getProfile(){

        ProfileDto profile = profileService.getProfile();
        return buildResponse(profile);
    }


}
