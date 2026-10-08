package com.rohit.ecommerce_backend.mappers;

import com.rohit.ecommerce_backend.dto.OrderItemResponseDto;
import com.rohit.ecommerce_backend.dto.OrderResponseDto;
import com.rohit.ecommerce_backend.entities.Order;
import com.rohit.ecommerce_backend.entities.OrderItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {

    public OrderResponseDto toOrderResponseDto(Order order) {

        List<OrderItemResponseDto> items = order.getItems()
                .stream()
                .map(this::toOrderItemResponseDto)
                .toList();

        Long totalAmount = items.stream()
                .mapToLong(OrderItemResponseDto::getTotalAmount)
                .sum();

        return OrderResponseDto.builder()
                .orderId(order.getId())
                .userId(order.getUser().getId())
                .userName(order.getUser().getName())
                .address(order.getAddress())
                .phoneNumber(order.getPhoneNumber())
                .orderStatus(order.getOrderStatus())
                .paymentStatus(order.getPaymentStatus())
                .items(items)
                .totalAmount(totalAmount)
                .createdDate(order.getCreatedDate())
                .updatedDate(order.getUpdatedDate())
                .build();
    }

    public OrderItemResponseDto toOrderItemResponseDto(OrderItem orderItem) {

        Long totalAmount =
                orderItem.getUnitPrice() * orderItem.getQuantity();

        return OrderItemResponseDto.builder()
                .orderItemId(orderItem.getId())
                .productId(orderItem.getProduct().getId())
                .productName(orderItem.getProduct().getName())
                .imageUrl(orderItem.getProduct().getImageUrl())
                .quantity(orderItem.getQuantity())
                .unitPrice(orderItem.getUnitPrice())
                .totalAmount(totalAmount)
                .orderItemStatus(orderItem.getOrderItemStatus())
                .build();
    }

}
