package com.rohit.ecommerce_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {

    private String name;

    private String email;

    private String mobileNumber;

    private String address;

    private Instant createdAt;

    private Instant updatedAt;
}
