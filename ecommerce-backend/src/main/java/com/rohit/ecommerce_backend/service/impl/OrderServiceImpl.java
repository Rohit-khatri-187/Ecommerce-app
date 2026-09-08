package com.rohit.ecommerce_backend.service.impl;


import com.rohit.ecommerce_backend.dto.AddToOrderRequestDto;
import com.rohit.ecommerce_backend.dto.OrderItemResponseDto;
import com.rohit.ecommerce_backend.entities.*;
import com.rohit.ecommerce_backend.enums.ORDER_ITEM_STATUS;
import com.rohit.ecommerce_backend.enums.ORDER_STATUS;
import com.rohit.ecommerce_backend.enums.PAYMENT_STATUS;
import com.rohit.ecommerce_backend.exceptions.EmptyException;
import com.rohit.ecommerce_backend.exceptions.NotFoundException;
import com.rohit.ecommerce_backend.mappers.OrderMapper;
import com.rohit.ecommerce_backend.repositories.CartItemRepository;
import com.rohit.ecommerce_backend.repositories.OrderItemRepository;
import com.rohit.ecommerce_backend.repositories.OrderRepository;
import com.rohit.ecommerce_backend.repositories.ProductRepository;
import com.rohit.ecommerce_backend.service.CartItemService;
import com.rohit.ecommerce_backend.service.OrderService;
import com.rohit.ecommerce_backend.service.ProductService;
import com.rohit.ecommerce_backend.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {


    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;
    private final CartItemService cartItemService;
    private final SecurityUtils securityUtils;
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final OrderMapper orderMapper;


    @Transactional
    public List<OrderItemResponseDto> placeOrder(AddToOrderRequestDto dto) {

        User user = securityUtils.getCurrentUser();

        List<CartItem> cartItems =
                cartItemRepository.findCartItemsByUserId(user.getId());

        if (cartItems.isEmpty()) {
            throw new EmptyException("Cart is empty");
        }

        Order order = Order.builder()
                .user(user)
                .address(dto.getAddress())
                .phoneNumber(dto.getPhoneNumber())
                .orderStatus(ORDER_STATUS.CONFIRMED)
                .paymentStatus(PAYMENT_STATUS.PENDING)
                .build();

        List<CartItem> processedCartItems = new ArrayList<>();

        for (CartItem cartItem : cartItems) {

            Product product = cartItem.getProduct();

            int updated = productRepository.findByProductIdForStockUpdate(product.getId(),cartItem.getQuantity());

            if(updated == 0){
                continue;
            }

            OrderItem orderItem = OrderItem.builder()
                    .product(cartItem.getProduct())
                    .unitPrice(cartItem.getProduct().getPrice())
                    .quantity(cartItem.getQuantity())
                    .orderItemStatus(ORDER_ITEM_STATUS.CONFIRMED)
                    .build();

            order.addOrderItem(orderItem);
            processedCartItems.add(cartItem);
        }

        Order savedOrder = orderRepository.save(order);

        cartItemService.clearCartItem(processedCartItems);

        return savedOrder.getItems()
                .stream()
                .map(orderMapper::toOrderItemResponseDto)
                .toList();
    }

    @Transactional
    public void cancelOrderItem(Long orderItemId){

        User user = securityUtils.getCurrentUser();

        OrderItem orderItem = orderItemRepository.findOrderItemForUser(orderItemId,user.getId())
                .orElseThrow(() -> new NotFoundException("OrderItem not found with id : " + orderItemId));

        if (orderItem.getOrderItemStatus() == ORDER_ITEM_STATUS.CANCELLED) {
            throw new RuntimeException(
                    "Order item is already cancelled"
            );
        }

        if (orderItem.getOrderItemStatus() == ORDER_ITEM_STATUS.SHIPPED) {
            throw new RuntimeException(
                    "Shipped item cannot be cancelled"
            );
        }

        if (orderItem.getOrderItemStatus() == ORDER_ITEM_STATUS.COMPLETED) {
            throw new RuntimeException(
                    "Delivered item cannot be cancelled"
            );
        }

        productService.updateStockAfterOrderItemCancelation(orderItem.getProduct().getId(),orderItem.getQuantity());
        orderItem.setOrderItemStatus(ORDER_ITEM_STATUS.CANCELLED);


    }



}
