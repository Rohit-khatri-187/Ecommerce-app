package com.rohit.ecommerce_backend.dto;


import lombok.*;


import java.time.Instant;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponseDto {

    private Long id;

    private String name;

    private Integer quantity;

    private Long totalAmount;

    private String imageUrl;

    private Instant createdDate;

    private Instant updatedDate;

}
