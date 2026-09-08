package com.rohit.ecommerce_backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {

    @Schema(defaultValue ="test" )
    @NotBlank(message = "Name is required")
    private String name;

    @Schema(defaultValue = "test@gmail.com")
    @NotBlank(message = "Email is required")
    @Email(message = "Please Enter a valid Email")
    private String email;

    @Schema(defaultValue = "test12345")
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 32, message = "Password must be between 8 and 32 characters")
    private String password;

}
