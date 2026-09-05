package com.rohit.ecommerce_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddToCartDto{
    private Long productId;
    private Integer quantity;
}
