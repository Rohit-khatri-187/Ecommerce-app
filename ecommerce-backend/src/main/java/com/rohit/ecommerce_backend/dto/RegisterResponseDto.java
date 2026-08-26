package com.rohit.ecommerce_backend.dto;

import com.rohit.ecommerce_backend.enums.ROLE;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponseDto {

    private Long id;
    private String name;
    private String email;
    private ROLE role;
    private Instant createdDate;
    private Instant updatedDate;
}
