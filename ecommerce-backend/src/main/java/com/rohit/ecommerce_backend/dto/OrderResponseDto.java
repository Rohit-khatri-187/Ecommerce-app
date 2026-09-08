package com.rohit.ecommerce_backend.dto;

import com.rohit.ecommerce_backend.enums.ORDER_STATUS;
import com.rohit.ecommerce_backend.enums.PAYMENT_STATUS;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {

    private Long orderId;

    private Long userId;
    private String userName;

    private String address;
    private String phoneNumber;

    private ORDER_STATUS orderStatus;

    private PAYMENT_STATUS paymentStatus;

    private List<OrderItemResponseDto> items;

    private Long totalAmount;

    private Instant createdDate;
    private Instant updatedDate;
}
