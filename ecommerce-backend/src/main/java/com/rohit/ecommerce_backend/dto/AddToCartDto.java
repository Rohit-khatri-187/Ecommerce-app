package com.rohit.ecommerce_backend.dto;

public record AddToCartDto(
     Long productId,
     Integer quantity
) {
}
