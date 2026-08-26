package com.rohit.ecommerce_backend.entities;

import com.rohit.ecommerce_backend.enums.ROLE;
import com.rohit.ecommerce_backend.utils.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Table(name = "users")
@Entity
public class User extends BaseEntity {

    @NotBlank
    private String name;

    @Column(unique = true)
    @NotBlank
    @Email(message = "Please Enter a valid Email")
    private String email;

    @NotBlank
    private String password;

    private String mobileNumber;

    private String Address;

    @Enumerated(EnumType.STRING)
    private ROLE role;


}
