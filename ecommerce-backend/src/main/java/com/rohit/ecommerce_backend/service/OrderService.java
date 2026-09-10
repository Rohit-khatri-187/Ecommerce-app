package com.rohit.ecommerce_backend.service;

import com.rohit.ecommerce_backend.dto.AddToOrderRequestDto;
import com.rohit.ecommerce_backend.dto.OrderItemResponseDto;

import java.util.List;

public interface OrderService {

    List<OrderItemResponseDto> placeOrder(AddToOrderRequestDto dto);

    void cancelOrderItem(Long orderItemId);

    public List<OrderItemResponseDto> getAllOrderItems();

}
