package com.rohit.ecommerce_backend.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddToOrderRequestDto {

    private String address;

    private String phoneNumber;

}
