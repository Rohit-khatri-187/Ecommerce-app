package com.rohit.ecommerce_backend.service;

import com.rohit.ecommerce_backend.dto.AddToCartDto;
import com.rohit.ecommerce_backend.dto.CartItemResponseDto;
import com.rohit.ecommerce_backend.entities.CartItem;

import java.util.List;

public interface CartItemService {

    CartItemResponseDto addToCart(AddToCartDto addToCartDto);

    List<CartItemResponseDto> getAllCartsItem();

    void clearCartItem(List<CartItem> processedCartItem);

    void deleteCartItem(Long id);

}
