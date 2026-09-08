package com.rohit.ecommerce_backend.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponseDto {

    private Long orderItemId;

    private Long productId;
    private String productName;
    private String imageUrl;

    private Integer quantity;
    private Long unitPrice;
    private Long totalAmount;
}