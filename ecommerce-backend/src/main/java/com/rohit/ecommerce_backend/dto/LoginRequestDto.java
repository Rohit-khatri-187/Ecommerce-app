package com.rohit.ecommerce_backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {

    @Schema(defaultValue = "khatrirohit1000@gmail.com")
    @NotBlank(message = "Email is required")
    @Email(message = "Please Enter a valid Email")
    private String email;

    @Schema(defaultValue = "rohit123")
    @NotBlank(message = "Password is required")
    private String password;
}
