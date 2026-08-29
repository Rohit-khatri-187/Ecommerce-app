package com.rohit.ecommerce_backend.service.impl;


import com.rohit.ecommerce_backend.dto.AddToOrderRequestDto;
import com.rohit.ecommerce_backend.dto.OrderItemResponseDto;
import com.rohit.ecommerce_backend.dto.OrderResponseDto;
import com.rohit.ecommerce_backend.entities.CartItem;
import com.rohit.ecommerce_backend.entities.Order;
import com.rohit.ecommerce_backend.entities.OrderItem;
import com.rohit.ecommerce_backend.entities.User;
import com.rohit.ecommerce_backend.enums.ORDER_STATUS;
import com.rohit.ecommerce_backend.repositories.CartItemRepository;
import com.rohit.ecommerce_backend.repositories.OrderItemRepository;
import com.rohit.ecommerce_backend.repositories.OrderRepository;
import com.rohit.ecommerce_backend.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl {


    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;
    private final SecurityUtils securityUtils;

    @Transactional
    public OrderResponseDto addToOrder(AddToOrderRequestDto dto) {

        User user = securityUtils.getCurrentUser();

        List<CartItem> cartItems =
                cartItemRepository.findCartItemsByUserId(user.getId());

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = Order.builder()
                .user(user)
                .address(dto.getAddress())
                .phoneNumber(dto.getPhoneNumber())
                .orderStatus(ORDER_STATUS.PENDING)
                .build();

        for (CartItem cartItem : cartItems) {

            OrderItem orderItem = OrderItem.builder()
                    .product(cartItem.getProduct())
                    .unitPrice(cartItem.getProduct().getPrice())
                    .quantity(cartItem.getQuantity().longValue())
                    .build();

            order.addItem(orderItem);
        }

        Order savedOrder = orderRepository.save(order);

        cartItemRepository.deleteAllCartItemsByUserId(user.getId());

        return toDto(savedOrder);
    }

    public OrderResponseDto toDto(Order order) {

        List<OrderItemResponseDto> items = order.getItems()
                .stream()
                .map(this::toOrderItemDto)
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
                .items(items)
                .totalAmount(totalAmount)
                .createdDate(order.getCreatedDate())
                .updatedDate(order.getUpdatedDate())
                .build();
    }

    private OrderItemResponseDto toOrderItemDto(OrderItem orderItem) {

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
                .build();
    }


}
