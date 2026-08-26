package com.rohit.ecommerce_backend.dto;

import com.rohit.ecommerce_backend.enums.ROLE;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginResponseDto {
    private String token;
    private ROLE role;
}
