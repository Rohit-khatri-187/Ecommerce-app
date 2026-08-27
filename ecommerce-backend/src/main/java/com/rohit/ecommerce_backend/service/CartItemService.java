package com.rohit.ecommerce_backend.service;

import com.rohit.ecommerce_backend.dto.AddToCartDto;
import com.rohit.ecommerce_backend.dto.CartItemResponseDto;

import java.util.List;

public interface CartItemService {

    CartItemResponseDto addToCart(AddToCartDto addToCartDto);

    List<CartItemResponseDto> getAllCartsItem();

    void deleteCartItem(Long id);

}
