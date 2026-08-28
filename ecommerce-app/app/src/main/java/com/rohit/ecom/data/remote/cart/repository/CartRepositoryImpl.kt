package com.rohit.ecom.data.remote.cart.repository

import com.rohit.ecom.data.remote.cart.CartApi
import com.rohit.ecom.data.remote.cart.dto.AddToCartDto
import com.rohit.ecom.data.remote.cart.dto.AddToCartResponseDto
import com.rohit.ecom.data.remote.cart.dto.GetCartItemsResponseDto
import com.rohit.ecom.domain.cart.repository.CartRepository

class CartRepositoryImpl(
    private val cartApi: CartApi
) : CartRepository {
    override suspend fun addToCart(addToCartDto: AddToCartDto): AddToCartResponseDto {
        return cartApi.addToCart(addToCartDto);
    }

    override suspend fun getAllProducts(): GetCartItemsResponseDto {
        return cartApi.getAllCartItems();
    }


}