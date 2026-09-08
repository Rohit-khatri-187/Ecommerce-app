package com.rohit.ecommerce_backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddToCartDto{
    @Schema(defaultValue = "1")
    private Long productId;
    @Schema(defaultValue = "2")
    private Integer quantity;
}
